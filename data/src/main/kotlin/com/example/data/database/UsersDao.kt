package com.example.data.database

import androidx.room.*
import com.google.android.material.circularreveal.CircularRevealHelper.Strategy
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<UserEntry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userEntry: UserEntry)

    @Delete
    suspend fun deleteUser(userEntry: UserEntry)
}
