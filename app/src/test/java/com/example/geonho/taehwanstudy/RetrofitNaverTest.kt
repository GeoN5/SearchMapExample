package com.example.geonho.taehwanstudy

import android.util.Log
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback

class RetrofitNaverTest {

    lateinit var retrofitNaver : RetrofitNaver

    @Before
    fun setUp() {
        retrofitNaver = RetrofitNaver.instance
    }

    @Test
    fun searchLocalTest() {
        retrofitNaver.searchLocal("서울").
                enqueue(object : Callback<Response> {
                    override fun onFailure(call: Call<Response>?, t: Throwable?) {

                    }

                    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                        Log.d("response","${response?.toString()}")
                    }

                })
        Thread.sleep(4000L)
    }
}