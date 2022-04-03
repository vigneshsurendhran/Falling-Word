package com.task.fallingwords.domain.usecase

import com.task.fallingwords.common.Resource
import com.task.fallingwords.data.jdo.Word
import com.task.fallingwords.domain.repository.IWordsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class GetWordsUseCaseTest {


    lateinit var getWordsUseCase: GetWordsUseCase
    lateinit var wordRepository: IWordsRepository
    lateinit var testData : MutableList<Word>

    init {
        testData = mutableListOf()
        testData.apply {
            add(Word("aaa","bbb"))
            add(Word("bbb","ccc"))
            add(Word("ccc","ddd"))
            add(Word("ddd","eee"))
            add(Word("eee","fff"))
            add(Word("fff","ggg"))
            add(Word("ggg","hhh"))
            add(Word("hhh","iii"))
            add(Word("iii","jjj"))
            add(Word("jjj","kkk"))
            add(Word("kkk","lll"))
            add(Word("lll","mmm"))
            add(Word("mmm","nnn"))
            add(Word("nnn","ooo"))
            add(Word("ooo","ppp"))
            add(Word("ppp","qqq"))
            add(Word("qqq","rrr"))
            add(Word("rrr","sss"))
            add(Word("sss","ttt"))
            add(Word("ttt","uuu"))
            add(Word("uuu","vvv"))
        }
    }

    @Before
    public fun setUp() {
        wordRepository = mockk()
        getWordsUseCase = GetWordsUseCase(wordRepository)
    }

    @Test
    fun `getRandomWordsWithTranslation, return random words from huge list ,success`() {
        coEvery { wordRepository.getWords() } returns testData
        runBlocking {
            var actualResult = getWordsUseCase.getRandomWordsWithTranslation()
            System.out.print(actualResult.data.toString())
            assert(actualResult.data?.size == 10)
        }
    }

    @Test
    fun `getRandomWordsWithTranslation, return all words from the list ,failure`() {
        coEvery { wordRepository.getWords() } returns testData.subList(0, 5)
        runBlocking {
            var actualResult = getWordsUseCase.getRandomWordsWithTranslation()
            assert(actualResult.data?.size == 5)
        }
    }


    @Test
    fun `getRandomWordsWithTranslation, throws exception ,failure`() {
        coEvery { wordRepository.getWords() } throws FileNotFoundException()
        runBlocking {
            var actualResult = getWordsUseCase.getRandomWordsWithTranslation()
            assert(actualResult is Resource.Error)
        }
    }

}