package com.example.newsappwithjetback.domain.usecases.app_entry

import com.example.newsappwithjetback.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}