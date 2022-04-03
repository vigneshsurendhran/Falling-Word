package com.task.fallingwords.presentation.viewmodel

import androidx.lifecycle.viewmodel.compose.viewModel
import com.task.fallingwords.common.Resource
import com.task.fallingwords.domain.model.WordModel
import com.task.fallingwords.domain.usecase.GetWordsUseCase
import com.task.fallingwords.viewmodel.WordGameViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WordGameViewModelTest{

    lateinit var viewmodel : WordGameViewModel

    lateinit var getWordsUseCase: GetWordsUseCase

    var testData : Resource<List<WordModel>>

    init {
        var list  = mutableListOf<WordModel>()
        list.apply {
            add(WordModel("aaa","bbb", false, false, false))
            add(WordModel("bbb","ccc", false, false, false))
            add(WordModel("ccc","ddd", false, false, false))
            add(WordModel("ddd","eee", false, false, false))
            add(WordModel("eee","fff", false, false, false))
            add(WordModel("fff","ggg", false, false, false))
            add(WordModel("ggg","hhh", false, false, false))
            add(WordModel("hhh","iii", false, false, false))
            add(WordModel("iii","jjj", false, false, false))
            add(WordModel("jjj","kkk", false, false, false))
        }
        testData = Resource.Success(list)
    }

    @Before
    public fun setUp() {
        getWordsUseCase = mockk()
        viewmodel = WordGameViewModel(getWordsUseCase)
    }

    @Test
    fun generateQuestionTest() {
        coEvery { getWordsUseCase.getRandomWordsWithTranslation() } returns testData
        runBlocking {viewmodel.generateQuestion() }
        assert(viewmodel.currentQuestionSet.value != null)
    }

    @Test
    fun restartGameTest() {
        coEvery { viewmodel.generateQuestion() } returns Unit
        coEvery { viewmodel.startGame() } returns Unit
        runBlocking { viewmodel.restartGame() }
        coVerify{ viewmodel.generateQuestion()}
        coVerify { viewmodel.startGame()}
    }

    fun `validateAnswerAndShowNextQuestion, expected behaviour, success`(){
        coEvery { viewmodel.showQuestion() } returns Unit
        var input = WordModel("Hello", "Halo", false, true, false)
        runBlocking { viewmodel.validateAnswerAndShowNextQuestion(input) }
        assert(viewmodel.scoreState.value == 1)
    }

}