package com.task.fallingwords.presentation.state

data class GameViewState(val isLoading : Boolean = false, val score : Int, val word : String, val translation : String ,val isTestCompleted : Boolean, val isTranslationCorrect : Boolean)