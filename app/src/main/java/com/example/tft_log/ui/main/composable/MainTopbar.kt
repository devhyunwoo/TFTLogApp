package com.example.tft_log.ui.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tft_log.R
import com.example.tft_log.ui.theme.AppColors

@Composable
fun MainTopbar(
    modifier: Modifier = Modifier,
    onClickSearch: (String) -> Unit,
    textFieldState: TextFieldState,
    currentText: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = AppColors.Black, shape = CircleShape)
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            modifier = Modifier.size(30.dp), painter = painterResource(
                R.drawable.tft_logo
            ), contentDescription = "TFT 아이콘", tint = Color.Unspecified
        )
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            state = textFieldState,
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            onKeyboardAction = object : KeyboardActionHandler {
                override fun onKeyboardAction(performDefaultAction: () -> Unit) {
                    onClickSearch(currentText)
                }
            },
            textStyle = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = AppColors.Black
            ),
            decorator = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 2.dp, color = AppColors.Pink40, shape = CircleShape)
                        .background(color = AppColors.White, shape = CircleShape)
                        .padding(vertical = 10.dp, horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (currentText.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            innerTextField()
                            Text(
                                modifier = Modifier.padding(start = 3.dp),
                                text = "소환사명을 입력하세요",
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.W500,
                                color = AppColors.Black.copy(alpha = 0.3f)
                            )
                        }
                    } else {
                        innerTextField()
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    onClickSearch(currentText)
                                },
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "검색 아이콘",
                            tint = AppColors.Black
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainTopbarPreview() {
    MainTopbar(
        onClickSearch = {},
        textFieldState = rememberTextFieldState(),
        currentText = ""
    )
}
