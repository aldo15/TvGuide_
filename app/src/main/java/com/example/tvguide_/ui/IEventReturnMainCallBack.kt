package com.example.tvguide_.ui

import com.example.tvguide_.ui.modules.data.model.ShowItem

interface IEventReturnMainCallBack {
    fun CommunicationMain(showItem: ShowItem)
}