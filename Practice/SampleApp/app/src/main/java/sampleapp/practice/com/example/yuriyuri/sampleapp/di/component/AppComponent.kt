package sampleapp.practice.com.example.yuriyuri.sampleapp.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import sampleapp.practice.com.example.yuriyuri.sampleapp.di.module.ActivityModule
import sampleapp.practice.com.example.yuriyuri.sampleapp.di.module.AppModule
import sampleapp.practice.com.example.yuriyuri.sampleapp.di.module.ViewModelModule
import sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.SampleApp
import javax.inject.Singleton

/**
 * Modulesに必要なModuleを記載する.
 * SubComponentについては、親のModuleだけでOK.
 */
@Singleton
@Component(modules = [
    // ApplicationクラスでDaggerApplicationを使うときは必須.supportLibraryをつかはない場合はAndroidInjectionModuleを利用.
    AndroidSupportInjectionModule::class,
    AppModule::class,
    // 利用するModuleを追加する.
    ActivityModule::class,
    ViewModelModule::class])
interface AppComponent : AndroidInjector<SampleApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
        fun viewModelModule(viewModelModule: ViewModelModule): Builder
    }

    override fun inject(instance: SampleApp)
}
