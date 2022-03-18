package com.example.currency_app.dagger

import android.app.Application
import com.example.currency_app.dagger.component.AppComponent
import com.example.currency_app.dagger.component.DaggerAppComponent
import com.example.currency_app.dagger.component.DaggerViewModelComponent
import com.example.currency_app.dagger.component.ViewModelComponent
import com.example.currency_app.dagger.module.*

class CurrencyApp : Application() {
    companion object {
        lateinit var appComponent: AppComponent
        lateinit var appComponentViewModel: ViewModelComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .roomDbModule(RoomDbModule(this))
            .retrofitModule(RetrofitModule())
            .repositoryModule(RepositoryModule())
            .interactorModule(InteractorModule())
            .serviceModule(ServiceModule())
            .build()
        appComponentViewModel = DaggerViewModelComponent.builder()
            .appComponent(appComponent)
            .build()

    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

}