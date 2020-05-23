package com.example.liflow.di.modules

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SessionModule {
    @Provides
    @Named("sessionToken")
    fun provideSessionTokenProvider(sharedPreferences: SharedPreferences): String {
        val sessionTokenKey = "sessionToken"
        val sessionToken = sharedPreferences.getString("sessionToken", null)

        if (sessionToken == null) {
            sharedPreferences.edit().putString(sessionTokenKey, "fOlmNZnpfP")
            return "fOlmNZnpfP"
        }
        return sessionToken
    }
}