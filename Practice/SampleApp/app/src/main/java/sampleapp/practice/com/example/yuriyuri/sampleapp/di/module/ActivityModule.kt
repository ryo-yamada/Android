package sampleapp.practice.com.example.yuriyuri.sampleapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.MainActivity

@Module
interface ActivityModule {

    // MainActivityModuleがActivityModuleのSubcomponentとして扱われる
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class
    ])
    fun contributeMainActivity(): MainActivity

}
