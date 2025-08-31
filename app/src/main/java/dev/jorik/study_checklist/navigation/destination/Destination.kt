package dev.jorik.study_checklist.navigation.destination

import kotlinx.serialization.Serializable


@Serializable
internal sealed class Destination {

    @Serializable
    data object Courses : Destination()

    @Serializable
    data class Content(val id:Int = 0) : Destination()

    @Serializable
    data class Edit(val id:Int = 0) : Destination()

    @Serializable
    data class Create(val id:Int= 0) : Destination()

}

