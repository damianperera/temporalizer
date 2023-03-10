/*
 * Temporalizer - Manage Bi-Temporal History
 * Copyright (C) 2023 Damian Perera
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.perera.temporalizer.core

import io.perera.temporalizer.data.*
import io.perera.temporalizer.repository.MilestoneRepository
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.UUID

@Component
class EntityBasedTemporalizer(
    private val config: TemporalizerConfig,
    private val repository: MilestoneRepository
): Temporalizer, InputConverter {
    override fun get(input: Input, validFrom: Instant): Milestone? {
        if (!shouldPersist(config)) throw Exception("Temporalizer persistence is not enabled.")

        val entity = parseInput(input)
        val existingMilestones = repository.getRange(entity.type, entity.id, Instant.MIN, Instant.MAX)
        return MilestoneEngine.getFrom(validFrom, existingMilestones)
    }

    override fun set(milestone: Milestone) {
        if (!shouldPersist(config)) throw Exception("Temporalizer persistence is not enabled.")

        val entity = parseInput(milestone.entity)
        val existingMilestones = repository.getRange(entity.type, entity.id, Instant.MIN, Instant.MAX)

        val (previousMilestone, nextMilestone) = MilestoneEngine.getBeforeAndAfter(existingMilestones, milestone)

        if (previousMilestone != null) {
            previousMilestone.validTo = milestone.validFrom
            repository.update(previousMilestone)
        }

        if (nextMilestone != null) {
            nextMilestone.validFrom = milestone.validTo
            repository.update(nextMilestone)
        }

        repository.add(milestone.copy(id = UUID.randomUUID().toString()))
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
