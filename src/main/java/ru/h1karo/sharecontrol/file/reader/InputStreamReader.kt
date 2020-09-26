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

import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

abstract class InputStreamReader : ru.h1karo.sharecontrol.file.reader.Reader {
    override fun read(resource: Any, format: String): Map<String, Any> {
        if (resource !is InputStream) {
            throw IllegalArgumentException("%s can load messages only from input stream.".format(this::class.java))
        }

        val reader = InputStreamReader(resource)
        return this.loadFrom(reader)
    }

    abstract fun loadFrom(reader: Reader): Map<String, Any>

    override fun supports(resource: Any, format: String): Boolean = resource is InputStream
}