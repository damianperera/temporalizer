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

package io.perera.temporalizer.config

import io.perera.temporalizer.data.DataSourceConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "temporalizer")
@ComponentScan("io.perera.temporalizer")
class AutoConfiguration {

    @Value("\${postgres.url}")
    val dbURL: String? = null

    @Value("\${postgres.dbname}")
    val dbName: String? = null

    @Value("\${postgres.username}")
    val dbUsername: String? = null

    @Value("\${postgres.password}")
    val dbPassword: String? = null

    @Bean
    fun dataSourceConfig() =
        DataSourceConfig(
            url = dbURL ?: throw Exception("Not Defined - temporalizer.postgres.url"),
            name = dbName ?: throw Exception("Not Defined - temporalizer.postgres.dbname"),
            username = dbUsername ?: throw Exception("Not Defined - temporalizer.postgres.username"),
            password = dbPassword ?: throw Exception("Not Defined - temporalizer.postgres.password")
        )
}
