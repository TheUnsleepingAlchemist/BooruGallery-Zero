package com.tua.boorugalleryzero.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.tua.boorugalleryzero.R

@Composable
fun PreviewUnknown(
    modifier: Modifier = Modifier,
    fileExt: String
) {
    Box(
        modifier = modifier,
//        contentAlignment = Alignment.TopEnd
    ) {
        AsyncImage(
            R.drawable.missingformat,
            "missingFormat",
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            "$fileExt?? in the gallery app?? how queer!!\nihe never seen such a thing- i must inquire about\nthis further with my supervisor post-haste!!",
            minLines = 3,
            maxLines = 3,
            color = Color.Black,
            autoSize = TextAutoSize.StepBased(minFontSize = 5.sp, maxFontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.7f).align(BiasAlignment(0.9f,-0.75f))
        )
    }
}