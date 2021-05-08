package com.ertbaran.harcamatakip.api

import com.ertbaran.harcamatakip.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("v1/latest?access_key=8060aa5f56c3f56ebc0593859e831501&symbols=TRY,USD,GBP,BTC&format=1")
    suspend fun getPost(): Response<Post>
}