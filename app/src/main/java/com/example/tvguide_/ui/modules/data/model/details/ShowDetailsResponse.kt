package com.example.tvguide_

data class ShowDetailsResponse(
    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Int,
    val averageRuntime: Int,
    val premiered: String,
    val ended: String?,
    val officialSite: String?,
    val schedule: Schedule,
    val rating: Rating,
    val weight: Int,
    val network: Network,
    val webChannel: Any?,
    val dvdCountry: Any?,
    val externals: Externals,
    val image: Any?,
    val summary: String?,
    val updated: Long,
    val links: Links
)

data class Schedule(
    val time: String,
    val days: List<String>
)

data class Rating(
    val average: Double?
)

data class Network(
    val id: Int,
    val name: String,
    val country: Country,
    val officialSite: String?
)

data class Country(
    val name: String,
    val code: String,
    val timezone: String
)


data class Externals(
    val tvrage: Int?,
    val thetvdb: Int?,
    val imdb: String?
)

data class Links(
    val self: Link,
    val previousEpisode: Link
)

data class Link(
    val href: String
)


