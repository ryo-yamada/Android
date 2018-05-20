package sampleapp.practice.com.example.yuriyuri.sampleapp.di.module

import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.MainActivity
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags.TagsFragment

@Module
interface MainActivityModule {

    @Binds
    fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity


    @ContributesAndroidInjector
    fun contributeTagsFragment(): TagsFragment
}
