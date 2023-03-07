package com.example.domain.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val is_checked: Boolean?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
        println("edz. Boolean - ${Boolean::class.java.classLoader}")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeInt(id)
        parcel.writeString(avatar_url)
        parcel.writeValue(is_checked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
