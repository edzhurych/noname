package com.example.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<UserEntry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userEntry: UserEntry): Long

    @Delete
    suspend fun deleteUser(userEntry: UserEntry)
}
