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

package io.perera.temporalizer.repository

import io.perera.temporalizer.data.Milestone
import io.perera.temporalizer.data.MilestoneValidFrom
import io.perera.temporalizer.data.MilestoneValidTo
import org.springframework.stereotype.Repository

@Repository
class PostgresRepository: MilestoneRepository {
    override fun get(validFrom: MilestoneValidFrom): Milestone {
        TODO("Not yet implemented")
    }

    override fun getRange(validFrom: MilestoneValidFrom, validTo: MilestoneValidTo): List<Milestone> {
        TODO("Not yet implemented")
    }

    override fun set(milestone: Milestone) {
        TODO("Not yet implemented")
    }
}
