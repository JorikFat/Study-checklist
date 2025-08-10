package ru.pavlig43.prototype.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
data object Overview
@Serializable
internal sealed class Destination(val title: String) {

    @Serializable
    data object Courses : Destination("Курсы")

    @Serializable
    data object Content : Destination("Содержание")

    @Serializable
    data object Edit : Destination("Редактирование")

    @Serializable
    data object Create : Destination("Создание")

    companion object {
        fun allDestinations(): List<Destination> = listOf(
            Courses,
            Content,
            Edit,
            Create
        )
    }
}

