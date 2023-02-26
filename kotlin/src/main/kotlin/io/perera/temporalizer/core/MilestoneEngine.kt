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

import io.perera.temporalizer.data.Milestone
import io.perera.temporalizer.data.MilestoneValidFrom

class MilestoneEngine {
    companion object {
        fun getBeforeAndAfter(existingMilestones: List<Milestone>, currentMilestone: Milestone) =
            existingMilestones.sortedBy { it.validFrom }
                .let { sortedMilestones ->
                    val index = sortedMilestones.binarySearch { it.validFrom.compareTo(currentMilestone.validFrom) }
                    if (index < 0) {
                        Pair(sortedMilestones.getOrNull(-(index + 1) - 1), sortedMilestones.getOrNull(-(index + 1)))
                    } else {
                        Pair(sortedMilestones.getOrNull(index - 1), sortedMilestones.getOrNull(index + 1))
                    }
                }

        fun getFrom(validFrom: MilestoneValidFrom, existingMilestones: List<Milestone>) =
            existingMilestones.sortedBy { it.validFrom }
                .let { sortedMilestones ->
                    val index = sortedMilestones.binarySearch { it.validFrom.compareTo(validFrom) }
                    if (index < 0) {
                        sortedMilestones.getOrNull(-(index + 1) - 1)
                    } else {
                        sortedMilestones.getOrNull(index - 1)
                    }
                }
    }
}
