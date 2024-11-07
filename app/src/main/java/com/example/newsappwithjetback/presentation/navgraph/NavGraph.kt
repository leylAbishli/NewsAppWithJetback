package com.example.newsappwithjetback.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsappwithjetback.presentation.bookmark.BookmarkScreen
import com.example.newsappwithjetback.presentation.bookmark.BookmarkViewModel
import com.example.newsappwithjetback.presentation.news_navigator.NewsNavigator
import com.example.newsappwithjetback.presentation.onBoarding.OnBoardingScreen
import com.example.newsappwithjetback.presentation.onBoarding.OnBoardingViewModel

@Composable
fun NavGraph (
    startDestination: String
){
    val navController= rememberNavController()

    NavHost(navController=navController,startDestination=startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination=Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination=Route.NewsNavigatorNavigation.route
        ){
            composable(
                route = Route.NewsNavigatorNavigation.route
            ){
              NewsNavigator()
            }
        }
    }
}