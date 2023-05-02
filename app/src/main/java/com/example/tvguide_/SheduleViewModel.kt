package com.example.tvguide_

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvguide_.model.schedule.ScheduleResponse
import com.example.tvguide_.model.search.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SheduleViewModel : ViewModel() {
    private var scheduleLiveData = MutableLiveData<List<ScheduleResponse>>()
    private var searchLiveData = MutableLiveData<List<SearchResponse>>()

    fun getSchedule() {
        RetrofitInstance.api.getSchedule("US", "2023-04-30").enqueue(object :
            Callback<List<ScheduleResponse>> {
            override fun onResponse(call: Call<List<ScheduleResponse>>, response: Response<List<ScheduleResponse>>) {
                if (response.body()!=null){
                    scheduleLiveData.value = response.body()
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<List<ScheduleResponse>>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }

    fun getSearch( input : String) {
        RetrofitInstance.api.getShows(input).enqueue(object :
            Callback<List<SearchResponse>> {
            override fun onResponse(call: Call<List<SearchResponse>>, response: Response<List<SearchResponse>>) {
                if (response.body()!=null){
                    searchLiveData.value = response.body()
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<List<SearchResponse>>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }

    fun observeScheduleLiveData() : LiveData<List<ScheduleResponse>> {
        return scheduleLiveData
    }

    fun observeSearchLiveData() : LiveData<List<SearchResponse>> {
        return searchLiveData
    }
}
