package com.example.harrypotter.presenter.character_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.harrypotter.R

@Composable
fun DetailsRow(
    title: String,
    value: Any? = null
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.default_app_padding)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(id = R.dimen.medium_font_size).value.sp
        )
        Text(
            text = value.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            fontStyle = FontStyle.Italic,
            fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp
        )
    }
}