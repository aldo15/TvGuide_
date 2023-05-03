package com.example.tvguide_.ui.modules.data.model.schedule

data class ScheduleResponse(
    val id: Int,
    val url: String,
    val name: String,
    val season: Int,
    val number: Int,
    val type: String,
    val airdate: String,
    val airtime: String,
    val airstamp: String,
    val runtime: Int,
    val rating: Rating?,
    val image: Image?,
    val summary: String?,
    val show: Show,
    val _links: Links
)

data class Rating(
    val average: Double?
)

data class Image(
    val medium: String,
    val original: String
)

data class Show(
    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Int?,
    val averageRuntime: Int?,
    val premiered: String?,
    val ended: String?,
    val officialSite: String?,
    val schedule: Schedule?,
    val rating: Rating?,
    val weight: Int,
    val network: Network?,
    val webChannel: WebChannel?,
    val dvdCountry: String?,
    val externals: Externals,
    val image: Image?,
    val summary: String?,
    val updated: Int,
    val _links: Links
)

data class Schedule(
    val time: String,
    val days: List<String>
)

data class Network(
    val id: Int,
    val name: String,
    val country: Country?,
    val officialSite: String?
)

data class Country(
    val name: String,
    val code: String,
    val timezone: String
)

data class WebChannel(
    val id: Int,
    val name: String,
    val country: Any?
)

data class Externals(
    val tvrage: Int?,
    val thetvdb: Int?,
    val imdb: String?
)

data class Links(
    val self: Self,
    val show: Show
)

data class Self(
    val href: String
)
