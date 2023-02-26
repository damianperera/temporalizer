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
import io.perera.temporalizer.data.Milestone
import io.perera.temporalizer.data.Input
import io.perera.temporalizer.data.InputConverter
import org.springframework.stereotype.Component

@Component
class EntityBasedTemporalizer: Temporalizer, InputConverter {
    override fun getInitialMilestones(inputs: List<Input>): List<Milestone> {
        parseInput(inputs)
        TODO("Not yet implemented")
    }

    override fun getMilestoneChanges(input: Input, existingMilestones: List<Milestone>): List<Milestone> {
        val entity = parseInput(input)
        val milestones = existingMilestones.sortedBy { it.validFrom }

        TODO("Not yet implemented")
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
