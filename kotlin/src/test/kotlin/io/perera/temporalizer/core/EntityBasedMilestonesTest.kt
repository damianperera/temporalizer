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
import io.perera.temporalizer.data.Input
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EntityBasedMilestonesTest {

    private val stub = EntityBasedMilestones()

    @Test
    fun `getInitialMilestones throws exception if input is not valid`() {
        val input = object : Input{}
        val exception = assertThrows<Exception> {
            stub.getInitialMilestones(listOf(input))
        }

        exception.message shouldBe "Expected [Entity] but did not find any."
    }

}
