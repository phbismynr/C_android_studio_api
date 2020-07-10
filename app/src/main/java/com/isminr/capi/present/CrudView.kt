package com.isminr.capi.present

import com.isminr.capi.model.DataItem

interface CrudView {
    //Untuk get data
    fun onSuccessGet(data: List<DataItem>?)
    fun onFailedGet(msg : String)

    //Untuk Add
    fun successAdd(msg : String)
    fun errorAdd(msg: String)
}