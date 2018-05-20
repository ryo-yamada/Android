package sampleapp.practice.com.example.yuriyuri.sampleapp.data.api

import android.support.annotation.CheckResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.api.response.Tag
import javax.inject.Singleton

/**
 * Api サービス
 */
@Singleton
interface QiitaApi {

    @GET("/api/v2/tags")
    @CheckResult
    fun getTags(@Query("page") next: Int, @Query("per_page") perPage: Int,
                @Query("sort") sortType: String): Flowable<List<Tag>>
}
