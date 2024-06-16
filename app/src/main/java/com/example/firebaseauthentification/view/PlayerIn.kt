package com.example.firebaseauthentification.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.fontscaling.MathUtils
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.firebaseauthentification.R
import com.example.firebaseauthentification.domain.model.model.data.Podcast
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi",
    "AutoboxingStateValueProperty"
)
fun PlayerIn(context: Context) {
    lateinit var player: ExoPlayer
    ExoPlayer.Builder(context).build().also { player = it }
    val uiController = rememberSystemUiController()

    val colors = listOf(
        Color(0xD555E057),
        Color(0xD508960A),
        Color(0xD5A9EC6F),
        Color(0xD5D3EE58),
        Color(0xD5D3A80D),
        Color(0xD5EE9105),
        Color(0xD555B9E0),
    )
    val darkColor = listOf(
        Color(0xFF147716),
        Color(0xD5208122),
        Color(0xFF79BB3E),
        Color(0xFF9FB635),
        Color(0xD5D3A80D),
        Color(0xFF9C6005),
        Color(0xFF086488),

        )
    val colorIndex = remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(Unit) {
        colorIndex.value += 1
    }
    LaunchedEffect(colorIndex.value) {
        delay(2300)
        if (colorIndex.value < darkColor.lastIndex) {
            colorIndex.intValue += 1
        } else colorIndex.intValue = 0
    }

    val animatedColor by animateColorAsState(
        targetValue = colors[colorIndex.value], animationSpec = tween(2000)
    )
    val animatedDarkColor by animateColorAsState(
        targetValue = darkColor[colorIndex.value], animationSpec = tween(2000)
    )
    uiController.setStatusBarColor(animatedColor, darkIcons = false)
    uiController.setNavigationBarColor(animatedColor)

    val sounds = listOf(
        Podcast(
            name = "Путь в it",
            author = "Sam Three",
            cover = R.drawable.podcast,
            soundPodcast = R.raw.sound1

        ), Podcast(
            name = "О важном ",
            author = "Антон Степанеко",
            cover = R.drawable.podcsttwo,
            soundPodcast = R.raw.sound2

        ), Podcast(
            name = "Что-то есть",
            author = "Расул Мвгомедов",
            cover = R.drawable.podcasttfree,
            soundPodcast = R.raw.sound3

        )
    )

    val pagerState = rememberPagerState(pageCount = { sounds.count() })

    val playerIndex = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(pagerState.currentPage) {
        playerIndex.value = pagerState.currentPage
        player.seekTo(pagerState.currentPage, 0)
    }

    LaunchedEffect(Unit) {
        sounds.forEach {
            //val path =
            val mediaItem = MediaItem.fromUri(Uri.parse(path))
            player.addMediaItem(mediaItem)
        }

    }
    player.prepare()

    val playing = remember {
        mutableStateOf(false)
    }
    val currentPosition = remember {
        mutableLongStateOf(0)
    }
    val totalDuration = remember {
        mutableLongStateOf(0)
    }
    val progressSize = remember {
        mutableStateOf(IntSize(0, 0))
    }
    LaunchedEffect(player.isLoading) {
        playing.value = player.isPlaying
    }
    LaunchedEffect(player.currentPosition) {
        currentPosition.value = player.currentPosition
    }
    LaunchedEffect(player.duration) {
        if (player.duration > 0) {
            totalDuration.value = player.duration
        }
    }
    LaunchedEffect(player.currentMediaItemIndex) {
        playerIndex.value = player.currentMediaItemIndex
        pagerState.animateScrollToPage(playerIndex.value, animationSpec = tween(500))

    }

    var percentReached =
        currentPosition.value.toFloat() / (if (totalDuration.value > 0) totalDuration.value
        else 0)
            .toFloat()
    if (percentReached.isNaN()) {
        percentReached = 0f
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        animatedColor, animatedDarkColor
                    )
                )
            ), contentAlignment = Alignment.Center
    ) {
        val configuration = LocalConfiguration.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textColor by animateColorAsState(
                targetValue = if (animatedColor.luminance() > .5f) Color(
                    0xff313131
                ) else Color.White,
                animationSpec = tween(2000)
            )
            AnimatedContent(targetState = playerIndex.value, transitionSpec = {
                (scaleIn() + fadeIn()) togetherWith (scaleOut() + fadeOut())
            }) {
                Text(
                    text = sounds[it].name, fontSize = 52.sp, color = textColor
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                pageSize = PageSize.Fixed((configuration.screenWidthDp / (1.8)).dp),
                contentPadding = PaddingValues(horizontal = 85.dp)
            ) { page ->
                Card(modifier = Modifier
                    .size((configuration.screenWidthDp / (1.8)).dp)
                    .graphicsLayer {
                        val pageOffset =
                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                        val alphaLerp = MathUtils.lerp(
                            start = 0.4f,
                            stop = 1f,
                            amount = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        val scaleLerp = MathUtils.lerp(
                            start = 0.4f,
                            stop = 1f,
                            amount = 1f - pageOffset.coerceIn(0f, 5f)
                        )
                        alpha = alphaLerp
                        scaleX = scaleLerp
                        scaleY = scaleLerp
                    }
                    .border(3.dp, Color.White, CircleShape)
                    .padding(8.dp),
                    shape = CircleShape) {
                    Image(
                        painter = painterResource(id = sounds[page].cover),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }
            Spacer(modifier = Modifier.height(54.dp))
            Row(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = converterLongToText(currentPosition.value),
                    modifier = Modifier.width(55.dp),
                    color = textColor,
                    textAlign = TextAlign.Center
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(8.dp)
                        .padding(horizontal = 8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .onGloballyPositioned {
                            progressSize.value = it.size
                        }
                        .pointerInput(Unit) {
                            detectTapGestures {
                                val xPos = it.x
                                val whereIClicked =
                                    (xPos.toLong() * totalDuration.value) / progressSize.value.width.toLong()
                                player.seekTo(whereIClicked)
                            }
                        },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = if (playing.value) percentReached else 0f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0x414141))
                    )
                }
                Text(
                    text = converterLongToText(totalDuration.value),
                    modifier = Modifier.width(55.dp),
                    color = textColor,
                    textAlign = TextAlign.Center
                )

            }
            Spacer(modifier = Modifier.height(54.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Control(
                    icon = R.drawable.baseline_skip_previous_24,
                    size = 55.dp,
                    onClick = {
                        player.seekToPreviousMediaItem()

                    })

                Control(
                    icon = if (playing.value) R.drawable.baseline_pause_24
                    else R.drawable.baseline_play_arrow_24,
                    size = 80.dp,
                    onClick = {
                        if (playing.value) {
                            player.pause()
                        } else {
                            player.play()
                        }

                    })

                Control(
                    icon = R.drawable.baseline_skip_previous_24,
                    size = 55.dp,
                    onClick = {
                        player.seekToNextMediaItem()

                    })


            }

        }
    }
}


@Composable
fun Control(icon: Int, size: Dp, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(Color.White)
            .clickable {
                onClick
            }, contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(size / 2),
            painter = painterResource(id = icon),
            contentDescription = null
        )
    }
}

fun converterLongToText(long: Long): String {
    val sec = long / 1000
    val minutes = sec / 60
    val seconds = sec % 60

    val minutesString = if (minutes < 10) {
        "0${minutes}"
    } else {
        minutes.toString()
    }
    val secondsString = if (seconds < 10) {
        "0${seconds}"
    } else {
        seconds.toString()
    }
    return "$minutesString:$secondsString"

}
