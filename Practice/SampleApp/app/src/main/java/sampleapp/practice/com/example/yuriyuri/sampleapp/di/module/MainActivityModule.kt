package sampleapp.practice.com.example.yuriyuri.sampleapp.di.module

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import sampleapp.practice.com.example.yuriyuri.sampleapp.di.annotaion.ViewModelKey
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags.TagsFragment
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags.TagsViewModel

@Module
internal abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeTagsFragment(): TagsFragment

    @Binds
    @IntoMap
    @ViewModelKey(TagsViewModel::class)
    abstract fun bindTagsViewModel(viewModel: TagsViewModel): ViewModel
}
