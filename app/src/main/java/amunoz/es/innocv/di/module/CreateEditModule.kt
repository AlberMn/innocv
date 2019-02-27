package amunoz.es.innocv.di.module

import amunoz.es.innocv.INNOCVApi
import amunoz.es.innocv.interactor.CreateEditInteractor
import amunoz.es.innocv.interactor.CreateEditInteractorImpl
import amunoz.es.innocv.presenter.CreateEditPresenter
import amunoz.es.innocv.presenter.CreateEditPresenterImpl
import dagger.Module
import dagger.Provides


@Module
class CreateEditModule {

    @Provides
    fun provideCreateEditPresenter(createEditInteractor: CreateEditInteractor): CreateEditPresenter {
        return CreateEditPresenterImpl(createEditInteractor, null)
    }

    @Provides

    fun provideCreateEditInteractor(api: INNOCVApi): CreateEditInteractor {
        return CreateEditInteractorImpl(api, null)
    }
}