package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    Column {
                        TopAppBar(
                            title = { Text(stringResource(R.string.app_name), fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = colorResource(R.color.lemon_yellow)
                            )
                        )
                        LemonadeApp()
                    }
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (currentStep) {
            1 -> LemonTextAndImage(
                textLabelResourceId = R.string.lemon_select,
                drawableResourceId = R.drawable.lemon_tree,
                contentDescriptionResourceId = R.string.lemon_tree_content_description,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random() // nombre aléatoire entre 2 et 4
                }
            )
            2 -> LemonTextAndImage(
                textLabelResourceId = R.string.lemon_squeeze,
                drawableResourceId = R.drawable.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon_content_description,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount <= 0) {
                        currentStep = 3
                    }
                }
            )
            3 -> LemonTextAndImage(
                textLabelResourceId = R.string.lemon_drink,
                drawableResourceId = R.drawable.lemon_drink,
                contentDescriptionResourceId = R.string.lemonade_content_description,
                onImageClick = { currentStep = 4 }
            )
            4 -> LemonTextAndImage(
                textLabelResourceId = R.string.lemon_restart,
                drawableResourceId = R.drawable.lemon_restart,
                contentDescriptionResourceId = R.string.empty_glass_content_description,
                onImageClick = { currentStep = 1 }
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = Modifier
                .border(
                    2.dp,
                    colorResource(R.color.lemon_accent),
                    RoundedCornerShape(12.dp)
                )
                .background(
                    colorResource(R.color.lemon_light_green),
                    RoundedCornerShape(12.dp)
                )
                .clickable(onClick = onImageClick)
                .padding(32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(textLabelResourceId),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            LemonadeApp()
        }
    }
}