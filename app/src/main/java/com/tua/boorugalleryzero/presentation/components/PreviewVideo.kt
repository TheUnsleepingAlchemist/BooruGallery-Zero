package com.tua.boorugalleryzero.presentation.components

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.retain.RetainedEffect
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.MediaSourceFactory
import androidx.media3.exoplayer.upstream.DefaultAllocator
import androidx.media3.ui.compose.ContentFrame
import androidx.media3.ui.compose.buttons.MuteButton
import androidx.media3.ui.compose.buttons.PlayPauseButton
import androidx.media3.ui.compose.material3.indicator.PositionAndDurationText
import androidx.media3.ui.compose.material3.indicator.ProgressSlider

@OptIn(UnstableApi::class)
@Composable
fun PreviewVideo(
    modifier: Modifier = Modifier,
    url: String,
    mediaSourceFactory: () -> MediaSource.Factory,
    onClick: () -> Unit,
    canPlay: Boolean,
    autoplay: Boolean,
    loop: Boolean,
    mute: Boolean
) {
    val context = LocalContext.current.applicationContext

    val mediaItem = MediaItem.Builder()
        .setUri(url)
        .setMediaId(url)
        .build()

    val segmentSize = 131072 // is 128 KiB, was 65536 64 KiB
    val targetBufferBytes = 16 * segmentSize // 2 MB total memory buffer

    val loadControl: DefaultLoadControl = DefaultLoadControl.Builder()
        .setAllocator(DefaultAllocator(true, segmentSize))
        .setTargetBufferBytes(targetBufferBytes)
        .setBufferDurationsMs(15000, 30000, 5000, 10000)
        .build()

    val player = retain(url) {
        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory())
            .setLoadControl(loadControl)
            .build()
            .apply {
                if (mute) {
                    mute()
                }
                if (loop) {
                    repeatMode = ExoPlayer.REPEAT_MODE_ALL
                }

                setMediaItem(mediaItem,false)
                prepare()
            }
    }

    RetainedEffect(canPlay) {
        player.playWhenReady = canPlay && autoplay
        onRetire { player.playWhenReady = false }
    }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        ContentFrame(
            player = player,
            modifier = Modifier.fillMaxWidth(),
            keepContentOnReset = true
        )
        PlayerControls(
            player = player,
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .align(Alignment.BottomCenter)
        )
    }
    
}

@OptIn(UnstableApi::class)
@Composable
private fun PlayerControls(
    player: ExoPlayer,
    onClick: () -> Unit,
    modifier: Modifier
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            PositionAndDurationText(
                player
            )

//            Row(
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                TextIconButton(
//                    "favorite",
//                    {}
//                )
//                TextIconButton(
//                    "link",
//                    {}
//                )
//                TextIconButton(
//                    "download",
//                    {}
//                )
//                TextIconButton(
//                    "info",
//                    {}
//                )
//            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PlayPauseButton(player) {
                    FilledTonalIconButton(
                        onClick = {
                            this.onClick()
                        },
                        enabled = this.isEnabled
                    ) {
                        TextIcon(
                            iconName = if(showPlay) "pause" else "play_arrow",
                            isFilled = true
                        )
                    }
                }

                MuteButton(player) {
                    FilledTonalIconButton(
                        onClick = {
                            this.onClick()
                        },
                        enabled = this.isEnabled
                    ) {
                        TextIcon(
                            iconName = if(showMuted) "volume_off" else "volume_up",
                            isFilled = true
                        )
                    }
                }
            }

        }

        ProgressSlider(player)
    }

}