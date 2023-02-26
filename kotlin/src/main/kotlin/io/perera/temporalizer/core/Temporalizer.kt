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

import io.perera.temporalizer.data.Milestone
import io.perera.temporalizer.data.Input

interface Temporalizer {
    /** Generates a [List] of [Milestone] for the provided [List] of [Input]. */
    fun getInitialMilestones(inputs: List<Input>): List<Milestone>
    /** Returns the changes to [Milestone] for the provided [Input].*/
    fun getMilestoneChanges(input: Input, existingMilestones: List<Milestone>): List<Milestone>
}
