package ru.pavlig43.prototype.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
data object Overview
@Serializable
internal sealed class Destination(val title: String) {

    @Serializable
    data object Courses : Destination("Курсы")

    @Serializable
    data class Content(val id:Int = 0) : Destination("Содержание")

    @Serializable
    data class Edit(val id:Int = 0) : Destination("Редактирование")

    @Serializable
    data class Create(val id:Int= 0) : Destination("Создание")

    companion object {
        fun allDestinations(): List<Destination> = listOf(
            Courses,
            Content(),
            Edit(),
            Create()
        )
    }
}

