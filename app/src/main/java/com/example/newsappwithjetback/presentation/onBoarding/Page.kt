package com.example.newsappwithjetback.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.example.newsappwithjetback.R

data class Page(
    val title:String,
    val description:String,
    @DrawableRes val image:Int
)

val pages = listOf(
    Page(
        title = "Harris’ cash edge funds advertising blitz as Elon Musk cuts big check to House Republicans, new filings show",
        description = "Vice President Kamala Harris speaks at a campaign event in Atlanta on September 20, 2024.",
        image = R.drawable.imgnews
    ),
    Page(
        title = "Mexican president blames US in part for rise in violence in Sinaloa",
        description = "A truck on fire is seen on the streets of Culiacan, Sinaloa State, Mexico, on September 11, 2024. Ivan Medina/AFP/Getty Images",
        image = R.drawable.img2
    ),
    Page(
        title = "A once-in-1,000-year rainfall event from an unnamed storm floods homes and forces rescues in North Carolina",
        description = "Flooding in North Carolina swamps cars and roads near the Waterfront Villas and Marina in Carolina Beach on Monday, September 16. Courtesy Mike Scott",
        image = R.drawable.img3
    )
)