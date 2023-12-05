package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import network.dto.ProductItem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(products: List<ProductItem>) {
    val itemList = listOf("Trending", "Casual", "Origin")

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(10.dp))
            TopAppBarHeader()
            Category(itemList)
            Products(products = products)
            Spacer(modifier = Modifier.height(10.dp))
            OurClients()
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TopAppBarHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painterResource("avatar.webp"),
            contentDescription = "User Profile",
            modifier = Modifier.size(60.dp).padding(8.dp),
        )

        Text(
            text = "Hi, Alexa!",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.offset((-65).dp)
        )

        IconButton(onClick = {}) {
            Image(
                painterResource("dashboard_icon_grid.webp"),
                contentDescription = "Dashboard icon",
                modifier = Modifier.size(60.dp).padding(8.dp),
            )
        }
    }
}

@Composable
fun Category(itemList: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        items(itemList.size) { item ->
            Text(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
                text = itemList[item],
                color = if (item == 0) Color.Black else Color.LightGray
            )
        }
    }
}

@Composable
fun Products(products: List<ProductItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(450.dp)
            .padding(horizontal = 16.dp)
    ) {
        items(products) {
            ProductItemDesign(
                imageUrl = it.image,
                title = it.title,
                subtitle = it.category,
                price = "$${it.price}",
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }
    }
}

@Composable
fun ProductItemDesign(
    imageUrl: String,
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    price: String = "",
    backgroundColor: Color = Color.Transparent
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .width(120.dp)
                .height(120.dp)
                .fillMaxWidth(0.2f)
                .clip(RoundedCornerShape(20.dp))
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            KamelImage(
                resource = asyncPainterResource(imageUrl),
                contentDescription = "Product Image",
                modifier = Modifier.padding(8.dp),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Clip
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                fontSize = 13.sp,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .padding(4.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp, 16.dp),
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OurClients() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 26.dp, bottom = 20.dp)
    ) {
        Text(
            text = "Our Clients",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(10.dp))
        LazyRow(
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth()
        ) {
            item {
                OurClientsItems(
                    imagePainter = painterResource("client1.webp"),
                    title = "Ryan Ekstrom",
                    subtitle = "well protected\n" +
                            "from the sun!",
                    backgroundColor = Color.LightGray.copy(alpha = 0.4f)

                )
                Spacer(modifier = Modifier.padding(10.dp))
                OurClientsItems(
                    imagePainter = painterResource("client2.webp"),
                    title = "Haley Hogan",
                    subtitle = "well protected\n" +
                            "from the sun!",
                    backgroundColor = Color.LightGray.copy(alpha = 0.4f)
                )
            }
        }
    }
}
@Composable
fun OurClientsItems(
    imagePainter: Painter,
    title: String = "",
    subtitle: String = "",
    backgroundColor: Color = Color.Transparent
) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .width(90.dp)
                    .weight(0.3f)
                    .clip(RoundedCornerShape(20.dp)),
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = "client1.webp",
                    contentScale = ContentScale.Crop
                )

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Text(
                    text = subtitle,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}