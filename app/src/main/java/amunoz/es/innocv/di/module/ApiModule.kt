package amunoz.es.innocv.di.module

import amunoz.es.innocv.INNOCVApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return "https://hello-world.innocv.com/"
    }

    @Provides
    @Singleton
    fun provideOmbDbApi(retrofit: Retrofit): INNOCVApi {
        return retrofit.create(INNOCVApi::class.java)
    }
}
