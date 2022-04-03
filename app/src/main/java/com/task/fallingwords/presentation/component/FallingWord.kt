package com.task.fallingwords.presentation.component

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FallingWord(
    transitionState: TransitionState,
    translation: String,
    maxHeight: Dp,
    showNextQuestion: () -> Unit
) {
    val animationState = remember { mutableStateOf(TransitionState.Ready) }

    val modifier: Modifier

    val value = animateFloatAsState(
        targetValue = (if (animationState.value == TransitionState.Ready) 0.dp else maxHeight).value,
        animationSpec = if (animationState.value == TransitionState.Ready) tween(0) else tween(3000),
        finishedListener = {
        })

    modifier = Modifier.offset(
        y = value.value.dp,
    )

    Text(text = translation, modifier = modifier)

    animationState.value = transitionState

    if (animationState.value == TransitionState.Animate && value.value == maxHeight.value) {
        showNextQuestion()
    }
}


@Preview
@Composable
fun FallingWordPreview() {
}


enum class TransitionState {
    Ready, Animate
}
