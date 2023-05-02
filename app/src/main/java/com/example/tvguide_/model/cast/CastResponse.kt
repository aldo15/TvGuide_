package com.example.tvguide_.model.cast

data class CastResponse(
    val person: Person,
    val character: Character,
    val self: Boolean,
    val voice: Boolean
)

data class Person(
    val id: Int,
    val url: String,
    val name: String,
    val country: Country,
    val birthday: String?,
    val deathday: String?,
    val gender: String,
    val image: Image?,
    val updated: Long,
    val _links: Links
)

data class Country(
    val name: String,
    val code: String,
    val timezone: String
)

data class Image(
    val medium: String?,
    val original: String
)

data class Character(
    val id: Int,
    val url: String,
    val name: String,
    val image: Image,
    val _links: Links
)

data class Links(
    val self: Self
)

data class Self(
    val href: String
)
