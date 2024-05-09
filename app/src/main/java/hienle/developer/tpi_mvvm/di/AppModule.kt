package hienle.developer.tpi_mvvm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hienle.developer.tpi_mvvm.data.repository.AttractionRepositoryImp
import hienle.developer.tpi_mvvm.data.service.AttractionService
import hienle.developer.tpi_mvvm.domain.repository.AttractionRepository
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Hien on 5/9/24
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAttractionRepository(service: AttractionService): AttractionRepository {
        return AttractionRepositoryImp(service)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): AttractionService {
        return retrofit.create(AttractionService::class.java)
    }

}
