package com.example.domain

import com.example.domain.model.User

sealed class GetUsersResult

data class SuccessGetUsers(val value: List<User>) : GetUsersResult()

object FailureGetUsers : GetUsersResult()
