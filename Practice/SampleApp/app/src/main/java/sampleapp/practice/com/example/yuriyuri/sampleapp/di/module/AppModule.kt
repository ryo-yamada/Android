package sampleapp.practice.com.example.yuriyuri.sampleapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.api.QiitaApi
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.repository.TagDataRepository
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.repository.TagRepository
import sampleapp.practice.com.example.yuriyuri.sampleapp.util.AppSchedulerProvider
import sampleapp.practice.com.example.yuriyuri.sampleapp.util.SchedulerProvider
import javax.inject.Singleton

@Module
internal object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    @JvmStatic
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()


    /**
     * 課題:TagDataRepositoryの引数にQiitaApiとSchedulerProviderが渡せるようにする.
     */
    @Singleton
    @Provides
    @JvmStatic
    fun provideTagRepository(api: QiitaApi, schedulerProvider: SchedulerProvider): TagRepository =
            TagDataRepository(api, schedulerProvider)

}