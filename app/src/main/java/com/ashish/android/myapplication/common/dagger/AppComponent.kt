package com.ashish.android.myapplication.common.dagger

import com.ashish.android.myapplication.view.DealListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class, AppModule::class, RoomModule::class))
interface AppComponent {

    fun inject(activity: DealListActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun appModule(appModule: AppModule): Builder
        fun roomModule(roomModule: RoomModule): Builder
    }
}