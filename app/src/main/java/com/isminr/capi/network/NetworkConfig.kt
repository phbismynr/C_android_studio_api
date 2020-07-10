package com.isminr.capi.network

import com.isminr.capi.model.ResultStaff
import com.isminr.capi.model.ResultStatus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkConfig {

    fun getInterceptor() : OkHttpClient {
        val logging   = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }

    //RETROFIT
    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://192.168.43.187:80/android_api2/index.php/serverapi/")
//                FOR ANDROID PHYSIC
//            .baseUrl("http://192.168.43.187/android_api2/index.php/serverapi/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(Service::class.java)
}

interface Service {
    //Fungsi Create Data
    @FormUrlEncoded
    @POST("add")
    fun add(@Field("name") name : String,
            @Field("hp") hp : String,
            @Field("alamat") alamat : String) :
            Call<ResultStatus>

    //Fungsi Get Data
    @GET("getData")
    fun getData() : Call<ResultStaff>

}