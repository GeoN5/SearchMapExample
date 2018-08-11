package com.example.geonho.taehwanstudy

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverService {

    @GET("/v1/search/local.json")
     fun search(@Query(value = "query",encoded = true) keyWord:String): Call<Response>

}
data class Response(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Item>
)

data class Item(
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: String,
    val mapy: String
)