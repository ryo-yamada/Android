package sampleapp.practice.com.example.yuriyuri.sampleapp.di.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.MainActivity

@Module
internal abstract class ActivityModule {

    // MainActivityModuleがActivityModuleのSubcomponentとして扱われる
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class
    ])
    internal abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}