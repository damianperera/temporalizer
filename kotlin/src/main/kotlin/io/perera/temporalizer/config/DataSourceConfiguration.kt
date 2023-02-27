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

package io.perera.temporalizer.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.perera.temporalizer.data.DataSourceConfig
import io.perera.temporalizer.data.TemporalizerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration(
    private val config: TemporalizerConfig
) {

    @Bean
    fun postgresDataSource(): DataSource {
        val hikariConfig = HikariConfig()
        hikariConfig.jdbcUrl = "${config.jdbc.url}/${config.jdbc.name}"
        hikariConfig.username = config.jdbc.username
        hikariConfig.password = config.jdbc.password

        return HikariDataSource(hikariConfig)
    }

}
