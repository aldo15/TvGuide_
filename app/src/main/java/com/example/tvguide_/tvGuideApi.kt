package com.example.tvguide_

import com.example.tvguide_.model.cast.CastResponse
import com.example.tvguide_.model.schedule.ScheduleResponse
import com.example.tvguide_.model.search.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface tvGuideApi {
    @GET("schedule?")
    fun getSchedule(
        @Query("country") country : String,
        @Query("date") date : String) : Call<List<ScheduleResponse>>

    @GET("search/shows?")
    fun getShows(
        @Query("q") query : String) : Call<List<SearchResponse>>

    @GET
    fun getShowDetails(@Url url: String) : Call<ShowDetailsResponse>

    @GET
    fun getCast(@Url url: String) : Call<List<CastResponse>>
}