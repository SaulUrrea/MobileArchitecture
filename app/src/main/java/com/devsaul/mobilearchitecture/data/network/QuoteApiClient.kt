package com.devsaul.mobilearchitecture.data.network

import com.devsaul.mobilearchitecture.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    @GET("/.json")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>
}