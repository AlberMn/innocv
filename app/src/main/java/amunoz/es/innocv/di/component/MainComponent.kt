package amunoz.es.innocv.di.component

import amunoz.es.innocv.di.ActivityScope
import amunoz.es.innocv.di.module.MainModule
import amunoz.es.innocv.view.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(target: MainActivity)
}