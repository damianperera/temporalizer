/*
 *     Temporalizer - Manage Bi-Temporal History
 *     Copyright (C) 2023  Damian Perera
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.perera.temporalizer.core

import io.perera.temporalizer.data.Entity
import io.perera.temporalizer.data.Input
import io.perera.temporalizer.data.InputConverter
import io.perera.temporalizer.data.Milestone
import io.perera.temporalizer.repository.MilestoneRepository
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.UUID

@Component
class EntityBasedTemporalizer(
    private val repository: MilestoneRepository
): Temporalizer, InputConverter {
    override fun get(input: Input, validFrom: Instant) {
        val entity = parseInput(input)
        repository.get(entity.type, entity.id, validFrom)
    }

    override fun set(milestone: Milestone) {
        val entity = parseInput(milestone.entity)
        val existingMilestones = repository.getRange(entity.type, entity.id, Instant.MIN, Instant.MAX)

        val (before, after) = existingMilestones.sortedBy { it.validFrom }
            .let { sortedObjects ->
                val index = sortedObjects.binarySearch { it.validFrom.compareTo(milestone.validFrom) }
                if (index < 0) {
                    Pair(sortedObjects.getOrNull(-(index + 1) - 1), sortedObjects.getOrNull(-(index + 1)))
                } else {
                    Pair(sortedObjects.getOrNull(index - 1), sortedObjects.getOrNull(index + 1))
                }
            }

        if (before != null) {
            before.validTo = milestone.validFrom
            repository.set(before)
        }

        if (after != null) {
            after.validFrom = milestone.validTo
            repository.set(after)
        }

        repository.set(milestone)
    }

    override fun parseInput(input: Input): Entity {
        if (input !is Entity) {
            throw Exception("Expected [Entity] as input.")
        }
        return input
    }

    override fun parseInput(inputs: List<Input>): List<Entity> =
        inputs.filterIsInstance<Entity>().ifEmpty {
            throw Exception("Expected [Entity] but did not find any.")
        }
}
