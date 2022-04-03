package com.task.fallingwords.data.datasource

import com.task.fallingwords.common.Resource
import com.task.fallingwords.data.jdo.Word

interface DataSource {
    suspend fun readWords() : List<Word>
}