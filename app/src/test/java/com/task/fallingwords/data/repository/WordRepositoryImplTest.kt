package com.task.fallingwords.data.repository

import com.task.fallingwords.data.datasource.DataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WordRepositoryImplTest {
    private lateinit var wordRepository: WordRepositoryImpl
    private lateinit var mockDataSource: DataSource

    @Before
    fun setup() {
        mockDataSource = mockk()
        wordRepository = WordRepositoryImpl(mockDataSource)
    }

    @Test
    fun getWordsTest(){
        coEvery { mockDataSource.readWords() } returns ArrayList()
        runBlocking {  wordRepository.getWords() }
        coVerify { mockDataSource.readWords() }
    }

}