package amunoz.es.innocv.di.module

import amunoz.es.innocv.INNOCVApi
import amunoz.es.innocv.interactor.MainInteractor
import amunoz.es.innocv.interactor.MainInteractorImpl
import amunoz.es.innocv.presenter.MainPresenter
import amunoz.es.innocv.presenter.MainPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainPresenter(mainInteractor: MainInteractor): MainPresenter {
        return MainPresenterImpl(mainInteractor, null)
    }

    @Provides

    fun provideListingInteractor(api: INNOCVApi): MainInteractor {
        return MainInteractorImpl(api, null)
    }
}