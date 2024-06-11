package com.example.firebaseauthentification.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseauthentification.R
import com.example.firebaseauthentification.domain.model.data.BottomMenuContent
import com.example.firebaseauthentification.domain.model.data.Course
import com.example.firebaseauthentification.ui.theme.Beige1
import com.example.firebaseauthentification.ui.theme.Beige2
import com.example.firebaseauthentification.ui.theme.Beige3
import com.example.firebaseauthentification.ui.theme.Blue1
import com.example.firebaseauthentification.ui.theme.Blue2
import com.example.firebaseauthentification.ui.theme.Blue3
import com.example.firebaseauthentification.ui.theme.Green1
import com.example.firebaseauthentification.ui.theme.Green10
import com.example.firebaseauthentification.ui.theme.Green2
import com.example.firebaseauthentification.ui.theme.Green20
import com.example.firebaseauthentification.ui.theme.Green3
import com.example.firebaseauthentification.ui.theme.Orange1
import com.example.firebaseauthentification.ui.theme.Orange2
import com.example.firebaseauthentification.ui.theme.Orange3
import com.example.firebaseauthentification.ui.theme.PurpleGrey40
import com.example.firebaseauthentification.ui.theme.PurpleGrey80
import com.example.firebaseauthentification.ui.theme.Violet1
import com.example.firebaseauthentification.ui.theme.Violet2
import com.example.firebaseauthentification.ui.theme.Violet3

@Composable
fun HomeScreens(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .background(Green3)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipSection(
                navController = NavController
            )
            SuggestionSection()
            CourseSection(
                courses = listOf(
                    Course(
                        title = "geek of the year",
                        R.drawable.baseline_headphones_24,
                        Blue1, Blue2, Blue3
                    ),
                    Course(
                        title = "How does AI Works",
                        R.drawable.baseline_groups_3_24,
                        Green1, Green2, Green3
                    ),
                    Course(
                        title = "Advance python Course",
                        R.drawable.baseline_tab_24,
                        Violet1, Violet2, Violet3
                    ),
                    Course(
                        title = "Advance Java Course",
                        R.drawable.baseline_play_arrow_24,
                        Beige1, Beige2, Beige3
                    ),
                    Course(
                        title = "prepare for aptitude test",
                        R.drawable.baseline_fax_24,
                        Orange1, Orange2, Orange3
                    ), Course(
                        title = "How does AI Works",
                        R.drawable.baseline_auto_stories_24,
                        Blue1, Blue2, Beige3
                    )
                )
            )
        }
    }
}


@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier,
    activeHighlightColor: Color = Green10,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = PurpleGrey80,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Green10)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighLightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor

            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighLightColor: Color = Green10,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = PurpleGrey80,
    onItemClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isSelected) activeHighLightColor
                    else Color.Transparent
                )
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor
                else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title, color = if (isSelected) activeTextColor
            else inactiveTextColor
        )

    }
}


@Composable

@Preview(showBackground = true)
fun GreetingSection(
    name: String = "Geeks"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good morning, $name",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "We wish you have a good day!", style = MaterialTheme.typography.bodyMedium
            )
        }
        Icon(
            Icons.Filled.Search,
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

    }
}

@Composable
fun ChipSection(
    navController: NavController.Companion,
) {
    val bottomMenuContent = listOf("Популярное", "Скидки", "За бонусы")
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(bottomMenuContent.size) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) Green20
                        else Green10
                    )
                    .padding(15.dp)) {
                Button({}) {
                    Text(text = bottomMenuContent[it], color = Color.White)
                }

            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun SuggestionSection(
    color: Color = Green10
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 15.dp)
    ) {
        Column {
            Text(
                text = "Daily Coding",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "do at least * 3-10 problems / day",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(PurpleGrey40)
                .padding(10.dp)
        ) {
            Icon(
                Icons.Filled.PlayArrow,
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }

    }
}

@Composable
fun CourseSection(courses: List<Course>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "courses",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(15.dp),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 7.5.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(courses.size) {
                CourseItem(course = courses[it])
            }
        }
    }
}

private fun LazyGridItemScope.CourseItem(course: Course) {
}

@Composable
fun CourseItem(
    course: Course,
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(course.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight


        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawPath(
                path = lightColoredPath, color = course.mediumColor
            )
            drawPath(
                path = lightColoredPath, color = course.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),

            ) {
            Text(
                text = course.title,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                Icons.Filled.AccountBox,
                contentDescription = course.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Button(onClick = { }) {
                Text(
                    text = "Start",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(PurpleGrey40)
                        .padding(vertical = 6.dp, horizontal = 15.dp)
                )
            }


        }
    }
}

fun standardQuadFromTo(mediumColoredPoint2: Offset, mediumColoredPoint3: Offset) {


}