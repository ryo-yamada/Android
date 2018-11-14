package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation

import android.support.v7.app.AppCompatDelegate
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import sampleapp.practice.com.example.yuriyuri.sampleapp.di.component.DaggerAppComponent
import sampleapp.practice.com.example.yuriyuri.sampleapp.di.module.NetworkModule

/**
 * Application
 */
open class SampleApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this) // AppComponentのBuilderで設定
                .networkModule(NetworkModule.instance) // AppComponentのBuilderで設定
                .build()
    }
}