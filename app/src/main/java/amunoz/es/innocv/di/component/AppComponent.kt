package amunoz.es.innocv.di.component

import amunoz.es.innocv.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ApiModule::class])
interface AppComponent {
    fun plus(mainModule: MainModule): MainComponent
    fun plus(createEditModule: CreateEditModule): CreateEditComponent
}
