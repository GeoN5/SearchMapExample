package com.example.geonho.taehwanstudy


import com.example.geonho.taehwanstudy.util.createRetrofit
import retrofit2.Call

class RetrofitNaver private constructor() {

    private val naverService: NaverService

    init {
        val retrofit = createRetrofit()
        naverService = retrofit.create(NaverService::class.java)
    }

    /**
     * Retrofit Search url
     */
    fun searchLocal(userKeyword: String): Call<Response> {
        return naverService.search(userKeyword)
    }

    companion object {

        private var retrofitNaver: RetrofitNaver? = null

        val instance: RetrofitNaver
            get() {
                if (retrofitNaver == null) {
                    synchronized(RetrofitNaver::class.java) {
                        if (retrofitNaver == null) {
                            retrofitNaver = RetrofitNaver()
                        }
                    }
                }
                return retrofitNaver!!
            }
    }
}

