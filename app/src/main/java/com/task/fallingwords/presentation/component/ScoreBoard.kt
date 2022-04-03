package com.task.fallingwords.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.fallingwords.domain.model.WordModel

@Composable
fun ScoreBoard(score : Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
    ) {
        Text(text = "Score : ${score}", fontSize = 18.sp,  fontWeight = FontWeight.Bold, modifier = Modifier.padding(15.dp,15.dp))
    }
}

@Preview
@Composable
fun ScoreBoardPreview() {
    ScoreBoard(score = 10)
}
