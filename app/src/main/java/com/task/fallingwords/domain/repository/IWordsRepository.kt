package com.task.fallingwords.domain.repository

import com.task.fallingwords.common.Resource
import com.task.fallingwords.data.jdo.Word
import java.io.FileNotFoundException

interface IWordsRepository {

    @Throws(FileNotFoundException::class)
    suspend fun getWords() : List<Word>
}