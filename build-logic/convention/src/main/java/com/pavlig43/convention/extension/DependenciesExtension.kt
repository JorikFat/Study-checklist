package com.pavlig43.convention.extension

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(dependency: Provider<MinimalExternalModuleDependency>){
    add("implementation",dependency)
}
//internal fun DependencyHandlerScope.debugImplementation(dependency: Provider<Dependency>){
//    add("debugImplementation",dependency)
//}

internal fun DependencyHandlerScope.debugImplementation(dependency: Provider<MinimalExternalModuleDependency>){
    add("debugImplementation",dependency)
}


internal fun DependencyHandlerScope.detektPlugins(dependency: Provider<MinimalExternalModuleDependency>){
    add("detektPlugins",dependency)
}
internal fun DependencyHandlerScope.testImplementation(dependency: Provider<MinimalExternalModuleDependency>){
    add("testImplementation",dependency)
}

internal fun DependencyHandlerScope.androidTestImplementation(dependency: Provider<MinimalExternalModuleDependency>){
    add("androidTestImplementation",dependency)
}

