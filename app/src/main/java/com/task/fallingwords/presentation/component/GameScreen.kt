package com.task.fallingwords.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.fallingwords.domain.model.WordModel


@Composable
fun GameScreen(wordModel: WordModel?, score : Int, checkAnswerAndMoveToNextQuestion: (wordModel : WordModel) -> Unit, showNextQuestion: () -> Unit) {
    wordModel?.let {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ScoreBoard(score = score)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .weight(1f, false),
                elevation = 4.dp,
            ) {
                Text(
                    text = " ${wordModel.word}  ${wordModel.translatedWord}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(15.dp, 15.dp)
                )
            }

            BoxWithConstraints(
                Modifier
                    .fillMaxHeight()
                    .weight(8f, false)
                    .align(Alignment.CenterHorizontally)
                    .background(Color.Red)
            ) {
                FallingWord(wordModel?.timerStarted?.let { if (it) TransitionState.Animate else TransitionState.Ready }, wordModel.translatedWord, maxHeight, showNextQuestion)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .weight(1f, false),
            ) {

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    FloatingActionButton(
                        onClick = {
                            wordModel?.let {
                                checkAnswerAndMoveToNextQuestion(it.apply { isUserAnswerIsCorrect = if(isTranslationIsCorrect) true else false})
                            }
                        },
                        backgroundColor = Color.Green,
                    ) {
                        Icon(
                            Icons.Filled.Done,
                            contentDescription = "Correct",
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            tint = Color.White
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    }

                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    FloatingActionButton(
                        onClick = { wordModel?.let {
                            checkAnswerAndMoveToNextQuestion(it.apply { isUserAnswerIsCorrect = if(!isTranslationIsCorrect) true else  false })
                        }},
                        backgroundColor = Color.Red,

                        ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Wrong",
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            tint = Color.White
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun GameScreenPreview() {
}
