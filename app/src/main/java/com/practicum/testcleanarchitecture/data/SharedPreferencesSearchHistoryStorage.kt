package com.practicum.testcleanarchitecture.data

import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPreferencesSearchHistoryStorage(
    private val prefs: SharedPreferences,
    private val gson: Gson,
) : SearchHistoryStorage