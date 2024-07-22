package com.examen.vacaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vacaciones.database.DBHelper
import vacaciones.screen.CameraScreen
import vacaciones.screen.DetailScreen
import vacaciones.screen.FormScreen
import vacaciones.screen.HomeScreen
import vacaciones.viewmodel.FormRecepcionViewModel


class MainActivity : ComponentActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbHelper = DBHelper(this)
        setContent {
            Navigation(dbHelper)
        }
    }


    @Composable
    fun Navigation(dbHelper: DBHelper) {
        val navController = rememberNavController()
        val formRecepcionVm: FormRecepcionViewModel by viewModels()

        NavHost(navController = navController, startDestination = "home_screen") {
            composable("home_screen") {
                HomeScreen(navController)
            }
            composable("form_screen") {
                FormScreen(navController)
            }
            composable("detail_screen/{lugarId}") { backStackEntry ->
                val lugarId = backStackEntry.arguments?.getString("lugarId")
                if (lugarId != null) {
                    DetailScreen(navController, lugarId, formRecepcionVm)
                }
            }
            composable("camera_screen") { backStackEntry ->
                val lugarId = backStackEntry.arguments?.getString("lugarId")
                if (lugarId != null) {
                    CameraScreen(navController)
                }
            }
        }
    }
}



