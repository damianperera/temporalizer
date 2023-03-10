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

import io.perera.temporalizer.data.Input
import io.perera.temporalizer.data.Milestone
import io.perera.temporalizer.data.TemporalizerConfig
import java.time.Instant

interface Temporalizer {
    /** Get temporal milestone. */
    fun get(input: Input, validFrom: Instant): Milestone?
    /** Set milestone to date, backdate or postdate. */
    fun set(milestone: Milestone)

    fun shouldPersist(config: TemporalizerConfig) = config.enablePersistence
}
