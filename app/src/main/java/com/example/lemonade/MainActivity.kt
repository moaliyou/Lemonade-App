package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    MakingLemonadeScreen(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun MakingLemonadeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var stepCounter by remember {
            mutableStateOf(1)
        }

        var squeezeCounter by remember {
            mutableStateOf(0)
        }

        when (stepCounter) {
            1 -> {
                ImageButtonAndText(
                    imageResourceId = R.drawable.lemon_tree,
                    contentDescriptionId = R.string.lemon_tree_content_description,
                    textResourceId = R.string.lemon_tree_label
                ) {
                    stepCounter++
                    squeezeCounter = (2..4).random()
                }
            }
            2 -> {
                ImageButtonAndText(
                    imageResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionId = R.string.glass_of_lemonade_content_description,
                    textResourceId = R.string.glass_of_lemonade_label
                ) {
                    squeezeCounter--
                    if (squeezeCounter == 0)
                        stepCounter++
                }
            }
            3 -> {
                ImageButtonAndText(
                    imageResourceId = R.drawable.lemon_drink,
                    contentDescriptionId = R.string.lemon_content_description,
                    textResourceId = R.string.lemon_label
                ) {
                    stepCounter++
                }
            }
            4 -> {
                ImageButtonAndText(
                    imageResourceId = R.drawable.lemon_restart,
                    contentDescriptionId = R.string.empty_glass_content_description,
                    textResourceId = R.string.empty_glass_label
                ) {
                    stepCounter = 1
                }
            }
        }
    }

}

@Composable
fun ImageButtonAndText(
    imageResourceId: Int,
    contentDescriptionId: Int,
    textResourceId: Int,
    onImageClick: () -> Unit

) {
    ButtonWithImage(imageResourceId, contentDescriptionId, onImageClick)
    Spacer(modifier = Modifier.height(18.dp))
    Text(
        text = stringResource(textResourceId),
        fontSize = 18.sp
    )
}

@Composable
fun ButtonWithImage(
    painterValue: Int,
    contentDescriptionId: Int,
    onImageClick: () -> Unit

) {

    Button(
        shape = RoundedCornerShape(52.dp),
        contentPadding = PaddingValues(24.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer),
        onClick = onImageClick
    ) {
        GetImage(
            painter = painterResource(painterValue),
            contentDescription = stringResource(contentDescriptionId)
        )
    }
}

@Composable
fun GetImage(painter: Painter, contentDescription: String?) {
    Image(
        painter = painter,
        contentDescription = contentDescription
    )
}