package com.example.graphiclayermodifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.graphiclayermodifier.MainDestinations.MAIN_SCREEN
import com.example.graphiclayermodifier.MainDestinations.TARGET_SCREEN
import com.example.graphiclayermodifier.MainDestinations.TARGET_TYPE
import com.example.graphiclayermodifier.ui.theme.GraphicLayerModifierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphicLayerModifierTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    NavGraph()
}

object MainDestinations {
    const val MAIN_SCREEN = "mainScreen"
    const val TARGET_SCREEN = "targetScreen"
    const val TARGET_TYPE = "targetType"
}

enum class TARGET_VIEW_TYPE(val value: String, val func: @Composable () -> Unit) {
    FLIPPING_CARD("Flipping Card", { FlippingCard() }),
    POKER_CARD("Poker Card", { PokerCard() }),
    DOUBLE_SIDE_CARD("Double Size Card", { DoubleSideCard() }),
    GRAPHICS_LAYER_SETTING("Graphics Layer Setting", { GraphicsLayerSetting() }),
    RENDER_EFFECT("Render Effect", { RenderEffect() }),
    WORDLE("Wordle Effect", { Wordle() })
}

@Composable
fun NavGraph(startDestination: String = MAIN_SCREEN) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MAIN_SCREEN) {
            MainScreen(actions)
        }
        composable(
            "$TARGET_SCREEN/{$TARGET_TYPE}",
            arguments = listOf(navArgument(TARGET_TYPE) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            ChildScreen(arguments.getString(TARGET_TYPE))
        }
    }
}

class MainActions(navController: NavHostController) {
    val mainScreen: () -> Unit = {
        navController.navigate(MAIN_SCREEN)
    }
    val targetScreen: (String) -> Unit = { setting ->
        navController.navigate("$TARGET_SCREEN/$setting")
    }
}

@Composable
fun MainScreen(actions: MainActions) {
    @Composable
    fun ColumnScope.MyButton(
        title: TARGET_VIEW_TYPE
    ) {
        Button(
            onClick = { actions.targetScreen(title.value) },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Text(title.value)
        }
    }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            enumValues<TARGET_VIEW_TYPE>().forEach {
                MyButton(it)
            }
        }
    }
}

@Composable
fun ChildScreen(animationSetting: String?) {
    enumValues<TARGET_VIEW_TYPE>().first { it.value == animationSetting }.func.invoke()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GraphicLayerModifierTheme {
        Greeting("Android")
    }
}
