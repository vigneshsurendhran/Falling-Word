package com.task.fallingwords.di

import android.content.Context
import com.task.fallingwords.data.datasource.DataSource
import com.task.fallingwords.data.datasource.FileDataSource
import com.task.fallingwords.data.repository.WordRepositoryImpl
import com.task.fallingwords.domain.repository.IWordsRepository
import com.task.fallingwords.domain.usecase.GetWordsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataSource(@ApplicationContext context: Context) : DataSource {
        return FileDataSource(context)
    }

    @Provides
    @Singleton
    fun provideWordRepository(dataSource: DataSource) : IWordsRepository {
        return WordRepositoryImpl(dataSource)
    }


    @Provides
    @Singleton
    fun provideWordUseCase(repository: IWordsRepository) : GetWordsUseCase {
        return GetWordsUseCase(repository)
    }


}