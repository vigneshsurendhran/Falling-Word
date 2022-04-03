package com.task.fallingwords.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.task.fallingwords.domain.model.WordModel
import com.task.fallingwords.domain.usecase.GetWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class WordGameViewModel @Inject constructor(val getWordsUseCase: GetWordsUseCase) : ViewModel() {

    private val _wordModelState: MutableState<WordModel?> = mutableStateOf(null)
    val eventModelState: State<WordModel?> = _wordModelState

    private val _gameState = mutableStateOf(GameState.START)
    val gameState: State<GameState> = _gameState

    private var _scoreState = mutableStateOf(0)
    val scoreState: State<Int> = _scoreState

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var currentQuestionSet: MutableState<MutableList<WordModel>?> = mutableStateOf(null)


    var job: Job = Job()

    suspend fun generateQuestion() {
        currentQuestionSet.value = getWordsUseCase.getRandomWordsWithTranslation().data?.toMutableList()
    }

    suspend fun restartGame() {
        generateQuestion()
        startGame()
    }

    suspend fun startGame() {
        generateQuestion()
        _gameState.value = GameState.ONGAME
        showQuestion()
    }


    suspend fun showQuestion() {

        currentQuestionSet.value?.let { list ->
            if (list.size > 0) {
                list.get(0).let {
                    _wordModelState.value = WordModel(
                        it.word,
                        it.translatedWord,
                        it.isTranslationIsCorrect,
                        it.isUserAnswerIsCorrect,
                        false
                    )
                    delay(200)
                    _wordModelState.value = WordModel(
                        it.word,
                        it.translatedWord,
                        it.isTranslationIsCorrect,
                        it.isUserAnswerIsCorrect,
                        true
                    )
                    delay(3000)
                }

                currentQuestionSet.value?.let {
                    if (it.size > 0) {
                        it.removeAt(0)
                    }
                }
            } else {
                _gameState.value = GameState.COMPLETED
            }
        }
    }


    suspend fun validateAnswerAndShowNextQuestion(wordModel: WordModel) {
        if (wordModel.isUserAnswerIsCorrect)
            _scoreState.value = _scoreState.value + 1
        showQuestion()
    }


}

enum class GameState {
    COMPLETED,
    START,
    ONGAME
}