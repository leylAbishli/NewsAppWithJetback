package com.example.newsappwithjetback.di

import android.app.Application
import androidx.room.Room
import com.example.newsappwithjetback.data.LocalUserManagerImpl
import com.example.newsappwithjetback.data.local.NewsDao
import com.example.newsappwithjetback.data.local.NewsDatabase
import com.example.newsappwithjetback.data.local.NewsTypeConvertor
import com.example.newsappwithjetback.data.remote.NewsApi
import com.example.newsappwithjetback.data.repository.NewsRepositoryImpl
import com.example.newsappwithjetback.domain.manager.LocalUserManager
import com.example.newsappwithjetback.domain.repository.NewsRepository
import com.example.newsappwithjetback.domain.usecases.app_entry.AppEntryUseCase
import com.example.newsappwithjetback.domain.usecases.app_entry.ReadAppEntry
import com.example.newsappwithjetback.domain.usecases.app_entry.SaveAppEntry
import com.example.newsappwithjetback.domain.usecases.news.DeleteArticle
import com.example.newsappwithjetback.domain.usecases.news.GetNews
import com.example.newsappwithjetback.domain.usecases.news.NewsUseCase
import com.example.newsappwithjetback.domain.usecases.news.SearchNews
import com.example.newsappwithjetback.domain.usecases.news.SelectArticle
import com.example.newsappwithjetback.domain.usecases.news.SelectArticles
import com.example.newsappwithjetback.domain.usecases.news.UpsertArticle
import com.example.newsappwithjetback.util.Constants.BASE_URL
import com.example.newsappwithjetback.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCase(localUserManager: LocalUserManager) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)

    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi,newsDao: NewsDao): NewsRepository = NewsRepositoryImpl(newsApi,newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository,newsDao: NewsDao): NewsUseCase {
        return NewsUseCase(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name=NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor()).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao= newsDatabase.newsDao


}