package com.example.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<UserEntry>>

    @Query("SELECT id FROM users")
    suspend fun getUserIds(): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userEntry: UserEntry): Long

    @Delete
    suspend fun deleteUser(userEntry: UserEntry)
}
