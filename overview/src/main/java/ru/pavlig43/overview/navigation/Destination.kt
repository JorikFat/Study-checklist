package ru.pavlig43.overview.navigation

sealed class Destination(val title: String) {
    data object Courses : Destination("Курсы")
    data object Content : Destination("Содержание")
    data object Edit : Destination("Редактирование")
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

