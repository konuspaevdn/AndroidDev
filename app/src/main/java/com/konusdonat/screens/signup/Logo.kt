package com.konusdonat.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konusdonat.R

@Composable
fun ColumnScope.Logo() {
    val logo = painterResource(id = R.drawable.logo)
    Spacer(modifier = Modifier.height(40.dp))
    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = stringResource(R.string.logo),
            color = Color.White,
            fontSize = 52.sp,
            fontFamily = FontFamily(Font(R.font.kelvinch_bolditalic, FontWeight.Bold)),
            lineHeight = 116.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = logo,
            contentDescription = "logotype",
            modifier = Modifier
                .align(alignment = Alignment.Bottom)
                .size(80.dp),
        )
    }
        Text(
            text = stringResource(R.string.motto),
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
}