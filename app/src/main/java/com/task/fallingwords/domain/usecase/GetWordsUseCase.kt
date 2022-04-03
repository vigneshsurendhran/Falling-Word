package com.task.fallingwords.domain.usecase

import com.task.fallingwords.common.Resource
import com.task.fallingwords.domain.model.WordModel
import com.task.fallingwords.domain.repository.IWordsRepository
import java.lang.Exception
import javax.inject.Inject
import kotlin.random.Random

class GetWordsUseCase @Inject constructor(private val repository: IWordsRepository) {

    suspend fun getRandomWordsWithTranslation(): Resource<List<WordModel>> {
        try {
            var result = repository.getWords()
            var set = mutableSetOf<WordModel>()
            var wordModel: WordModel

            if(result.size <= 10)  {
                var count = 1
                result.forEach {
                    if ( count % 2 == 0) {
                        wordModel = WordModel(it.text_eng, it.text_spa.reversed(), false)
                    } else {
                        wordModel = WordModel(it.text_eng, it.text_spa, true)
                    }
                    set.add(wordModel)
                    count+=1
                }
                return Resource.Success(set.toList())
            } else {
                while (set.size != 10) {
                    var randomIndex = Random.nextInt(result.size)
                    var word = result.get(randomIndex)
                    if (randomIndex % 2 == 0) {
                        wordModel = WordModel(word.text_eng, word.text_spa.reversed(), false)
                    } else {
                        wordModel = WordModel(word.text_eng, word.text_spa, true)
                    }
                    set.add(wordModel)
                }
                return Resource.Success(set.toList())
            }
        } catch (exception: Exception) {
            return Resource.Error(exception.message ?: "Something wrong")
        }
    }

}