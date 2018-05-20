package sampleapp.practice.com.example.yuriyuri.sampleapp.di.module

import dagger.Module
import dagger.Provides
import sampleapp.practice.com.example.yuriyuri.sampleapp.data.repository.TagRepository
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.tags.TagsViewModel
import sampleapp.practice.com.example.yuriyuri.sampleapp.util.SchedulerProvider
import javax.inject.Singleton

@Module
open class ViewModelModule {

    @Singleton
    @Provides
    fun provideTagsViewModel(repository: TagRepository, schedulerProvider: SchedulerProvider): TagsViewModel
            = TagsViewModel(repository, schedulerProvider)
}