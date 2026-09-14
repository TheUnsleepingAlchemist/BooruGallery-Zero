package com.tua.boorugalleryzero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tua.boorugalleryzero.data.persistent.defaultHomeSettings
import com.tua.boorugalleryzero.navigation.NavRoot
import com.tua.boorugalleryzero.ui.theme.BooruGalleryZeroTheme
import com.tua.boorugalleryzero.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val context = LocalContext.current.applicationContext

            val appViewModel = viewModel { AppViewModel(context) }

            val homeSettings by appViewModel.homeSetting.collectAsStateWithLifecycle(
                defaultHomeSettings
            )

            BooruGalleryZeroTheme {
                Surface {
                    NavRoot(
                        viewModel = appViewModel,
                        homeSettings = homeSettings
                    )
                }
            }
        }
    }
}
