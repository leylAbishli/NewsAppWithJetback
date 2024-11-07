package com.example.newsappwithjetback.presentation.detail.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsappwithjetback.R
import com.example.newsappwithjetback.ui.theme.NewsAppWithJetbackTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
     onBrowsingClick: () -> Unit,
     onShareClick: () -> Unit,
     onBackClick: () -> Unit,
     onBookmarkClick: () -> Unit,
){
     
     TopAppBar(title = {},modifier=Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background), colors = TopAppBarDefaults.mediumTopAppBarColors(
          containerColor = Color.Transparent,
          actionIconContentColor = colorResource(id = R.color.body),
          navigationIconContentColor = colorResource(id = R.color.body)
     ),
          navigationIcon = {
               IconButton(onClick = onBackClick) {
                    Icon(painter = painterResource(id = R.drawable.ic_back_arrow), contentDescription =null )
               }
          },
          actions = {
               IconButton(onClick = onBookmarkClick) {
                    Icon(painter = painterResource(id = R.drawable.ic_bookmark), contentDescription =null )
               }

               IconButton(onClick = onShareClick) {
                    Icon(imageVector = Icons.Default.Share, contentDescription =null )
               }

               IconButton(onClick = onBrowsingClick) {
                    Icon(painter = painterResource(id = R.drawable.ic_network), contentDescription =null )
               }
          }
     )

}
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopBarPreview(){
     NewsAppWithJetbackTheme {
          DetailsTopBar(
               onBrowsingClick = { /*TODO*/ },
               onShareClick = { /*TODO*/ },
               onBackClick = { /*TODO*/ }) {
               
          }
     }
}