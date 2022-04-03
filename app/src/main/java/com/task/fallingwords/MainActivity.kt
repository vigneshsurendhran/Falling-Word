package com.task.fallingwords

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.task.fallingwords.presentation.component.GameScreen
import com.task.fallingwords.viewmodel.GameState
import com.task.fallingwords.viewmodel.WordGameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WordGameViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (viewModel.gameState.value) {
                    GameState.START -> {
                        GameControl(
                            { CoroutineScope(Dispatchers.Main).launch { viewModel.startGame() } },
                            viewModel.gameState.value
                        )
                    }
                    GameState.ONGAME -> {
                        GameScreen(
                            wordModel = viewModel.eventModelState.value,
                            viewModel.scoreState.value,
                            {
                                CoroutineScope(Dispatchers.Main).launch {
                                    viewModel.validateAnswerAndShowNextQuestion(
                                        it
                                    )
                                }
                            },
                            {
                                CoroutineScope(Dispatchers.Main).launch { viewModel.showQuestion() }
                            })
                    }
                    GameState.COMPLETED -> {
                        GameControl(
                            { CoroutineScope(Dispatchers.Main).launch { viewModel.restartGame() } },
                            viewModel.gameState.value
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun GameControl(startGame: () -> Unit, gameState: GameState) {

        Button(
            onClick = {
                startGame()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green,
                contentColor = Color.White
            )
        ) {
            when (gameState) {
                GameState.START -> Text("Start Game")
                GameState.COMPLETED -> Text("Play Again")
            }
        }
    }


}
