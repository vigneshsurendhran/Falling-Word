package com.task.fallingwords.data.repository

import com.task.fallingwords.data.datasource.DataSource
import com.task.fallingwords.data.jdo.Word
import com.task.fallingwords.domain.repository.IWordsRepository
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor  (val dataSource : DataSource) : IWordsRepository{
    override suspend fun getWords(): List<Word>{
        return dataSource.readWords()
    }
}