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
import io.perera.temporalizer.data.EntityMilestone
import io.perera.temporalizer.data.Input
import io.perera.temporalizer.data.Milestone
import org.springframework.stereotype.Component

@Component
class EntityBasedMilestones: Temporalizer {
    override fun getInitialMilestones(inputs: List<Input>): List<Milestone> {
        val entities = getInputsAsEntities(inputs)
        TODO("Not yet implemented")
    }

    override fun getMilestoneChanges(input: Input, existingMilestones: List<Milestone>): List<Milestone> {
        val milestones = getMilestonesAsEntityMilestones(existingMilestones)
        TODO("Not yet implemented")
    }

    private fun getInputsAsEntities(inputs: List<Input>): List<Entity> =
        inputs.filterIsInstance<Entity>().ifEmpty {
            throw Exception("Expected [Entity] but did not find any.")
        }

    private fun getMilestonesAsEntityMilestones(milestones: List<Milestone>): List<EntityMilestone> =
        milestones.filterIsInstance<EntityMilestone>().ifEmpty {
            throw Exception("Expected [EntityMilestone] but did not find any.")
        }

}
