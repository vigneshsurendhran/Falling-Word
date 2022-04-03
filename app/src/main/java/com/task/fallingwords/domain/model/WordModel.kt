package com.task.fallingwords.domain.model

class WordModel(
    val word: String,
    val translatedWord: String,
    val isTranslationIsCorrect: Boolean,
    var isUserAnswerIsCorrect: Boolean = false,
    var timerStarted: Boolean = false,
    var isAnswered: Boolean = false
)