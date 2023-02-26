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

import io.perera.temporalizer.data.*
import org.springframework.stereotype.Repository
import javax.sql.DataSource

@Repository
class EntityMilestonePostgresRepository(
    private val postgresDataSource: DataSource
): MilestoneRepository {

    override fun getRange(
        entityType: EntityType,
        entityId: EntityId,
        validFrom: MilestoneValidFrom,
        validTo: MilestoneValidTo
    ): List<Milestone> {
        TODO("Not yet implemented")
    }

    override fun add(milestone: Milestone) {
        TODO("Not yet implemented")
    }

    override fun update(milestone: Milestone) {
        TODO("Not yet implemented")
    }
}
