package com.isminr.capi.present

import android.util.Log
import com.isminr.capi.model.ResultStaff
import com.isminr.capi.model.ResultStatus
import com.isminr.capi.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter(val crudView: CrudView) {
    //Fungsi GetData
    fun getData(){
        NetworkConfig.getService().getData()
            .enqueue(object : retrofit2.Callback<ResultStaff>{
                override fun onFailure(call: Call<ResultStaff>, t: Throwable) {
                    crudView.onFailedGet(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<ResultStaff>, response:
                Response<ResultStaff>
                ) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.staff
                            crudView.onSuccessGet(data)
                        } else{
                            crudView.onFailedGet("Error $status")
                        }                     }
                }

            })
    }

    //Add data
    fun addData(name : String, hp : String, alamat : String){
        NetworkConfig.getService()
            .add(name, hp, alamat)

            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAdd(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResultStatus>, response:
                Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAdd(response.body()?.pesan ?: "")
                    }else {
                        crudView.errorAdd(response.body()?.pesan ?: "")
                    }
                }

            })
    }
}