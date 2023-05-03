package com.example.tvguide_.ui.modules.showdatils.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvguide_.ShowDetailsResponse
import com.example.tvguide_.ui.modules.data.model.cast.CastResponse
import com.example.tvguide_.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowDetailsViewModel : ViewModel() {
    private var showDetailsLiveData = MutableLiveData<ShowDetailsResponse>()
    private var showCastLiveData = MutableLiveData<List<CastResponse>>()

    fun getShowDetails(link: String) {
        val id = link.split("/")
        val url = "https://api.tvmaze.com/shows/" + id[id.size - 1]
        RetrofitInstance.api.getShowDetails(url).enqueue(object :
            Callback<ShowDetailsResponse> {
            override fun onResponse(call: Call<ShowDetailsResponse>, response: Response<ShowDetailsResponse>) {
                if (response.body()!=null){
                    showDetailsLiveData.value = response.body()
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<ShowDetailsResponse>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }


    fun getCast(link: String){
        val id = link.split("/")
        val url = "https://api.tvmaze.com/shows/" + id[id.size - 1] + "/cast"
        RetrofitInstance.api.getCast(url).enqueue(object :
            Callback<List<CastResponse>> {
            override fun onResponse(call: Call<List<CastResponse>>, response: Response<List<CastResponse>>) {
                if (response.body()!=null){
                    showCastLiveData.value = response.body()
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<List<CastResponse>>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }

    fun observeShowDetailsLiveData() : LiveData<ShowDetailsResponse> {
        return showDetailsLiveData
    }

    fun observeCastLiveData() : LiveData<List<CastResponse>> {
        return showCastLiveData
    }

}
