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

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import org.reflections.Reflections
import ru.h1karo.sharecontrol.ShareControl
import java.lang.reflect.Modifier

abstract class AbstractModule : AbstractModule() {
    protected fun <T> bindSet(type: Class<T>, exclude: Set<Class<out T>> = setOf()) {
        val reflections = Reflections(ShareControl::class.java.`package`.name)
        val services = reflections.getSubTypesOf(type)

        services.removeIf { Modifier.isInterface(it.modifiers) || Modifier.isAbstract(it.modifiers) }
        exclude.forEach { services.remove(it) }

        val binder = Multibinder.newSetBinder(binder(), type)
        services.forEach { binder.addBinding().to(it) }
    }
}
