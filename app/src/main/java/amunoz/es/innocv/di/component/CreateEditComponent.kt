package amunoz.es.innocv.di.component

import amunoz.es.innocv.di.ActivityScope
import amunoz.es.innocv.di.module.CreateEditModule
import amunoz.es.innocv.view.CreateEditActivity
import dagger.Subcomponent


@ActivityScope
@Subcomponent(modules = [CreateEditModule::class])
interface CreateEditComponent {
    fun inject(target: CreateEditActivity)
}