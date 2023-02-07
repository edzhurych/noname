package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.User

@Entity("users")
data class UserEntry(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo("avatar_url") val avatarUrl: String,
    @ColumnInfo("is_checked") val isChecked: Boolean
)

fun List<UserEntry>.mapToUser() = map {
        User(
            id = it.id,
            login = it.name,
            avatar_url = it.avatarUrl,
            is_checked = it.isChecked
        )
    }

fun User.mapToUserEntry() =
    UserEntry(
        id = id,
        name = login,
        avatarUrl = avatar_url,
        isChecked = is_checked ?: false
    )
