package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntry::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao
}