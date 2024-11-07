package com.example.newsappwithjetback.presentation.news_navigator

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappwithjetback.R
import com.example.newsappwithjetback.domain.model.Article
import com.example.newsappwithjetback.presentation.bookmark.BookmarkScreen
import com.example.newsappwithjetback.presentation.bookmark.BookmarkViewModel
import com.example.newsappwithjetback.presentation.detail.DetailScreen
import com.example.newsappwithjetback.presentation.detail.DetailsEvent
import com.example.newsappwithjetback.presentation.detail.DetailsViewModel
import com.example.newsappwithjetback.presentation.home.HomeScreen

import com.example.newsappwithjetback.presentation.home.HomeViewModel
import com.example.newsappwithjetback.presentation.navgraph.Route
import com.example.newsappwithjetback.presentation.search.SearchScreen
import com.example.newsappwithjetback.presentation.search.SearchViewModel



@Composable
fun NewsNavigator() {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = remember(key1=backstackState) {
        when (backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible= remember(key1=backstackState,) {
    backstackState?.destination?.route== Route.HomeScreen.route ||
    backstackState?.destination?.route== Route.SearchScreen.route ||
    backstackState?.destination?.route== Route.BookmarkScreen.route
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(article = articles,
                    navigateToSearch = {
                        navigateToTab(navController=navController, route = Route.SearchScreen.route)
                    },
                    navigateToDetail = { article ->
                        navigateToDetails(
                            navController=navController,
                            article=article
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route){
                val viewModel : SearchViewModel= hiltViewModel()
                val state= viewModel.state.value
                SearchScreen(state = state, event =viewModel::onEvent, navigateToDetails = { article->
                    navigateToDetails(navController=navController, article = article)
                } )
            }
            composable(route = Route.DetailScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if(viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current,viewModel.sideEffect,Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.currentBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let {article->
                    DetailScreen(
                        article = article,
                        event = viewModel::onEvent,
                        navigateUp = { navController.navigateUp()
                            Log.e("mytag", "Navigating to details with article: ${article.title}")}
                    )
                }
            }
            composable(route = Route.BookmarkScreen.route){
                val viewModel:BookmarkViewModel= hiltViewModel()
                val state=viewModel.state.value
                BookmarkScreen(state = state, navigateToDetails = { article->
                    navigateToDetails(navController=navController,article=article)
                })
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop =
                true// eger home screene bir ence defe tiklanarsa multible instance yaranmamasi ucundu
        }
    }
}
private fun navigateToDetails(navController: NavController, article: Article) {
    Log.d("Navigation", "Navigating to details with article: ${article.title}")
        navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
        navController.navigate(route=Route.DetailScreen.route)

    }


