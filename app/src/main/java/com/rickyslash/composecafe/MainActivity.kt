package com.rickyslash.composecafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rickyslash.composecafe.components.HomeSection
import com.rickyslash.composecafe.components.SearchBar
import com.rickyslash.composecafe.model.*
import com.rickyslash.composecafe.ui.theme.ComposeCafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCafe()
        }
    }
}

@Composable
fun ComposeCafe() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Banner()
        HomeSection(
            title = stringResource(R.string.section_category),
            content = { CategoryRow() }
        )
        HomeSection(
            title = stringResource(R.string.section_favorite_menu),
            content = { MenuRow(dummyMenu) })
        HomeSection(
            title = stringResource(R.string.section_best_seller_menu),
            content = { MenuRow(dummyBestSellerMenu) })
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp))
        SearchBar()
    }
}

@Composable
fun CategoryRow() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier =  Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    ComposeCafeTheme {
        ComposeCafe()
    }
}

// ways in arranging component in Compose:
// - Column: vertically
// - Row: horizontally
// - Box: in front / behind another element

/* example:
@Composable
inline fun Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
)*/

// Arrangement: to arrange components inside some layout
// - Space Between
// - Space Around
// - Space Evenly
// - End (LTR)
// - Center
// - Start (LTR)

// Alignment: to organize position inside some layout
// - Column:
// --- Start, CenterHorizontally, End
// - Row:
// --- Top, CenterVertically, End
// - Box:
// --- TopStart, TopCenter, TopEnd
// --- CenterStart, Center, CenterEnd
// --- BottomStart, BottomCenter, BottomEnd

// use `Modifier` to align child element: `Modifier.align(Alignment.CenterVertically)`

// Weight: proportion of element inside a layout
// - `fill` to state whether an element fill the screen width or not
// - if only 1 element that given `weight`, that element will `fill` the layout while other element retain their size
/* example:
Row {
    ButtonWithText("1", Modifier.weight(1f))
    ButtonWithText("2", Modifier.weight(2f))
    ButtonWithText("3 fill true", Modifier.weight(weight = 3f, fill = true))
}*/

// Modifier: parameter to modify existing Composable Function, including:
// - size, range, display, position
// - action on certain condition
// - add action to some component
/* example:
Row(
    verticalAlignment = Alignment.CenterVertically
    modifier = modifier
        .fillMaxWidth()
        .clickable(onClick = {})
        .padding(4.dp),
)*/

// Size: set layout size in Compose using Modifier
// - fillMaxSize: component fill parent's layout
// - wrapContentSize: wrap the content inside
// - size: use exact value
// - requiredSize: use exact value, not depends on parent's layout
// - sizeIn: use range for size
// - can also use `width` & `height`

// Action: modifier to add action on any component
// - Clickable: add click action
// - Draggable: add drag action
// - Selectable: make component can be selected
// - Swipeable: add swipe action
/* example:
Row(
    modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = {}
)*/

// Padding, Offset, & Spacer:
// - Padding:
// --- all
// --- start, top, bottom, end
// --- horizontal, vertical
// - Offset: `Modifier.offset(x=8.dp, y = 30.dp)`
// - Spacer: same like padding but can't be clicked

// Drawing: add accessory to component:
// - border, clip, alpha, background, shadow

// in writing Modifier, order of writing is important:
/* this example made the padding area NOT clickable:
Row(
    modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = {})
        .padding(12.dp),
…*/

// Modifier as Parameter: best practice is to give default value to Modifier
/* example:
@Composable
fun ButtonWithText(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_500)),
        modifier = modifier.padding(4.dp)
    ) {
        Text(text, textAlign = TextAlign.Center)
    }
}*/

// Scaffold: is a layout structure following standard Material Design
// - it contains component called slot, such as App Bar, FAB, SnackBar, Bottom Navigation, & Navigation Drawer
/* usage of Scaffold:
@Composable
fun JetCoffeeApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Scaffold")
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
    )
}
*/

/* making custom Scaffold topBar:
Scaffold(
    topBar = {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
        ) {
            Image(
                painter = painterResource(R.drawable.dicoding_logo),
                contentDescription = null,
                modifier = Modifier.height(40.dp)
            )
        }
})*/

// Slot-based Layout is a layout that could be filled with another Composable Function, like Scaffold
/* customizing Slot-based Layout (TopAppBar):
@Composable
fun MyTopBar() {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        title = {
            Text(text = "My Scaffold")
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = {}) {
                    Text(text = "Call me")
                }
            }
        }
    )
}*/

// Custom Slot-Based Layout: Do it with HoF mechanism. use function type `@Composable ()-> Unit` as parameter. If the parameter is functional, make it nullable
/* example:
@Composable
fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation
)
*/