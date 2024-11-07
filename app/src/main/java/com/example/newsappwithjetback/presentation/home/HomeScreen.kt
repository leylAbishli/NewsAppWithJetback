package com.example.newsappwithjetback.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.newsappwithjetback.R
import com.example.newsappwithjetback.domain.model.Article
import com.example.newsappwithjetback.presentation.Dimens.MediumPadding1
import com.example.newsappwithjetback.presentation.common.ArticlesList
import com.example.newsappwithjetback.presentation.common.SearchBar
import com.example.newsappwithjetback.presentation.navgraph.Route

@Composable
fun HomeScreen(article: LazyPagingItems<Article>,navigateToSearch:()-> Unit,navigateToDetail:(Article)->Unit) {

    val titles by remember {
        derivedStateOf {
            if (article.itemCount > 10) {
                article.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        SearchBar(
            modifier=Modifier.padding(horizontal = MediumPadding1),
            text = "",
            readOnly = true,
            onClick = {
                navigateToSearch()
            },
            onValueChange = {},
            onSearch = {}
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(text=titles, modifier = Modifier
            .fillMaxWidth()
            .padding(start = MediumPadding1)
            .basicMarquee(),
            fontSize = 12.sp,
            color= colorResource(id = R.color.placeholder)
        )
        
        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticlesList(modifier = Modifier.padding(horizontal = MediumPadding1), article =article , onClick = {
            navigateToDetail(it)
        })
    }
}