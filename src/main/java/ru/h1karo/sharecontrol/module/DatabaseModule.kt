/*
 * This file is a part of ShareControl.
 *
 * ShareControl is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ShareControl is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ShareControl. If not, see <https://www.gnu.org/licenses/>.
 *
 * @copyright Copyright (c) 2020 ShareControl
 * @author Oleg Kozlov <h1karo@outlook.com>
 * @license GNU General Public License v3.0
 * @link https://github.com/h1karo/sharecontrol
 */

package ru.h1karo.sharecontrol.module

import com.google.inject.Injector
import com.google.inject.Key
import com.google.inject.Provides
import ru.h1karo.sharecontrol.configuration.entry.ParameterContainer
import ru.h1karo.sharecontrol.configuration.plugin.Database
import ru.h1karo.sharecontrol.database.DataSourceName
import ru.h1karo.sharecontrol.database.DatabaseType
import ru.h1karo.sharecontrol.database.annotation.DatabaseAnnotation

class DatabaseModule : AbstractModule() {
    override fun configure() {
        this.bindByAnnotation(DataSourceName::class.java, DatabaseAnnotation::class.java)
    }

    @Provides
    fun getType(injector: Injector): DatabaseType {
        val container = injector.getInstance(ParameterContainer::class.java)
        return container.get(Database) as DatabaseType
    }

    @Provides
    fun getDsn(injector: Injector): DataSourceName {
        val type = injector.getInstance(DatabaseType::class.java)
        return injector.getInstance(Key.get(DataSourceName::class.java, type.getAnnotation()))
    }
}
