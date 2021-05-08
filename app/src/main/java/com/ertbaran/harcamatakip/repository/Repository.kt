package com.ertbaran.harcamatakip.repository

import com.ertbaran.harcamatakip.api.RetrofitInstance
import com.ertbaran.harcamatakip.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}