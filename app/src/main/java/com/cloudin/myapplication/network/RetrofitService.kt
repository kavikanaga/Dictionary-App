package com.example.myapplication.network

import com.cloudin.myapplication.model.NoDefinition
import com.cloudin.myapplication.model.WordModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.collections.ArrayList

interface RetrofitService {
    @GET("{word}")
    fun getWordMeaning(
        @Path("word") word : String
    ): Observable<WordModel>
}