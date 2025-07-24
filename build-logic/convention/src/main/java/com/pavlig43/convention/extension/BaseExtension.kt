package com.pavlig43.convention.extension

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

internal val Project.projectJavaVersion: JavaVersion
    get() = JavaVersion.toVersion(libs.versions.java.get().toInt())

internal val Project.projectJvmTarget
    get() = JvmTarget.fromTarget(libs.versions.java.get())