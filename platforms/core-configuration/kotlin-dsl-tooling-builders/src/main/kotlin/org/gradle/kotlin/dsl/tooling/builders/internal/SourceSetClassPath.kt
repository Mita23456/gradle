/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.kotlin.dsl.tooling.builders.internal

import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.internal.classpath.ClassPath
import org.gradle.internal.classpath.DefaultClassPath
import org.gradle.kotlin.dsl.typeOf
import java.io.File


internal
sealed interface SourceSetClassPath


internal
object NoSourceSet : SourceSetClassPath


internal
data class ResolvedSourceSetClassPath(
    val projectDir: File,
    val precompiledScriptPluginsMetadataDir: File,
    val compileClasspath: ClassPath
) : SourceSetClassPath


internal
fun Project.findSourceSetClassPath(file: File): ResolvedSourceSetClassPath? =
    sourceSets?.find { file in it.allSource }?.let {
        resolveSourceSetClassPath(it)
    }


internal
fun Project.resolveSourceSetClassPath(it: SourceSet) =
    ResolvedSourceSetClassPath(projectDir, precompiledScriptPluginsMetadataDir, DefaultClassPath.of(it.compileClasspath))


private
val Project.sourceSets
    get() = extensions.findByType(typeOf<SourceSetContainer>())


private
val Project.precompiledScriptPluginsMetadataDir: File
    get() = layout.buildDirectory.dir("kotlin-dsl/precompiled-script-plugins-metadata").get().asFile
