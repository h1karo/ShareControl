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

package ru.h1karo.sharecontrol.file.reader

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.FileNotFoundException

class YamlFileReader : Reader {
    override fun read(resource: Any, format: String): Map<String, Any> {
        if (resource !is File) {
            throw IllegalArgumentException("%s can load only from the file.".format(this::class.java))
        }

        if (!resource.exists()) {
            val message = "File with path `%s` not found.".format(resource.path)
            throw FileNotFoundException(message)
        }

        return YamlConfiguration
            .loadConfiguration(resource)
            .getValues(true)
            .filter { it.value is String }
    }

    override fun supports(resource: Any, format: String): Boolean {
        return resource is File && listOf("yaml", "yml").contains(format)
    }
}
