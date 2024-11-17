package com.practicum.testcleanarchitecture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicum.testcleanarchitecture.data.db.dao.MovieDao
import com.practicum.testcleanarchitecture.data.db.entity.MovieEntity

@Database(version = 1, entities = [MovieEntity::class])
abstract class AppDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao

}