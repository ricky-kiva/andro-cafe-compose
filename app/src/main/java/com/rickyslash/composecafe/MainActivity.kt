package com.rickyslash.composecafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rickyslash.composecafe.ui.theme.ComposeCafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCafeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCafeTheme {
        Greeting("Android")
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