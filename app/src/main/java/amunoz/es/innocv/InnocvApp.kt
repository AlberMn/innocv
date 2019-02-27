package amunoz.es.innocv

import amunoz.es.innocv.di.component.AppComponent
import amunoz.es.innocv.di.component.CreateEditComponent
import amunoz.es.innocv.di.component.DaggerAppComponent
import amunoz.es.innocv.di.component.MainComponent
import amunoz.es.innocv.di.module.AppModule
import amunoz.es.innocv.di.module.CreateEditModule
import amunoz.es.innocv.di.module.MainModule
import amunoz.es.innocv.di.module.NetworkModule
import android.app.Application

class InnocvApp : Application (){

    lateinit var appComponent: AppComponent
    lateinit var mainComponent: MainComponent
    lateinit var createEditComponent: CreateEditComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .networkModule(NetworkModule())
                .appModule(AppModule(this)).build()
    }

    fun createMainComponent(): MainComponent {
        mainComponent = appComponent.plus(MainModule())
        return mainComponent
    }

    fun createCreateEditComponent (): CreateEditComponent {
        createEditComponent = appComponent.plus(CreateEditModule())
        return createEditComponent
    }

    fun releaseListingComponent() {

    }

}