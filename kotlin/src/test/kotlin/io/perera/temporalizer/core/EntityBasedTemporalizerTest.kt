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

import io.kotest.matchers.shouldBe
import io.perera.temporalizer.data.Entity
import io.perera.temporalizer.data.Input
import io.perera.temporalizer.data.Milestone
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EntityBasedTemporalizerTest {

    private val stub = EntityBasedTemporalizer()

    @Test
    fun `getInitialMilestones throws exception if input is not valid`() {
        val input = object : Input{}

        val exception = assertThrows<Exception> {
            stub.getInitialMilestones(listOf(input))
        }

        exception.message shouldBe "Expected [Entity] but did not find any."
    }

    @Test
    fun `getMilestoneChanges throws exception if input is not valid`() {
        val input = Entity()
        val milestone = object : Milestone{}

        val exception = assertThrows<Exception> {
            stub.getMilestoneChanges(input, listOf(milestone))
        }

        exception.message shouldBe "Expected [EntityMilestone] but did not find any."
    }

}
