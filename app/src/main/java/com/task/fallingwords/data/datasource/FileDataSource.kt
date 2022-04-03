package com.task.fallingwords.data.datasource

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.task.fallingwords.common.Resource
import com.task.fallingwords.data.jdo.Word
import java.util.*
import javax.inject.Inject
import java.util.ArrayList
import com.google.gson.Gson
import com.task.fallingwords.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception

class FileDataSource constructor(private val context: Context) : DataSource {

    override suspend fun readWords(): List<Word> {
        val inputStream = context.resources.openRawResource(R.raw.words)
        val buffer = ByteArray(inputStream.available())
        while (inputStream.read(buffer) !== -1);
        val json = String(buffer)
        val gson = Gson()
        val wordList: Array<Word> = gson.fromJson(json, Array<Word>::class.java)
        return wordList.asList()
    }

}