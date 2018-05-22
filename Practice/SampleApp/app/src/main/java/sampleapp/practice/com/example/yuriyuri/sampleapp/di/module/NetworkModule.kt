package sampleapp.practice.com.example.yuriyuri.sampleapp.di.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.api.ApplicationJsonAdapterFactory
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.api.QiitaApi
import javax.inject.Singleton

/**
 * NWクライアント提供クラス.</br>
 * APIに実際にアクセスする処理を提供する.
 */
@Module
open class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient()
            : OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient)
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
    @Provides
    fun provideQiitaApi(retrofit: Retrofit): QiitaApi {
        return retrofit.create(QiitaApi::class.java)
    }
}

