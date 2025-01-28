package at.ac.htlleonding.oldtimer_application.ui.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.htlleonding.oldtimer_application.ui.viewmodel.OldTimerViewModel

@Composable
fun MainScreen(
    viewModel: OldTimerViewModel = viewModel()
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list_oldtimers"
    ) {
        composable("list_oldtimers"){
           OldTimerList(
               viewModel = viewModel,
               onNavigateCreate = {navController.navigate("create_oldtimer")},
               onNavigateMore = {navController.navigate("about")}
           )
        }
        composable("about"){
            AboutScreen(
                onNavigateExit = {navController.navigate("list_oldtimers")},
            )
        }
        composable("create_oldtimer"){
            OldTimerInputScreen (
                onNavigateToList = {navController.navigate("list_oldtimers")},
            )
        }
    }
}
