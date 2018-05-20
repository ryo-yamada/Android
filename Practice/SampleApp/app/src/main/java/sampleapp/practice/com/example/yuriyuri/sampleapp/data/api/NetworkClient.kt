package sampleapp.practice.com.example.yuriyuri.sampleapp.data.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * NWクライアント提供クラス.</br>
 * APIに実際にアクセスする処理を提供する.
 */
open class NetworkClient {

    companion object {
        val instance = NetworkClient()
    }

    @Singleton
    private fun provideOkHttpClient()
            : OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    private fun provideRetrofit(okHttpClient: OkHttpClient)
            : Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://qiita.com")
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                        .add(ApplicationJsonAdapterFactory.INSTANCE)
                        .build()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
    }

    @Singleton
    fun provideQiitaApi(): QiitaApi {
        val retrofit = provideRetrofit(provideOkHttpClient())
        return retrofit.create(QiitaApi::class.java)
    }
}

