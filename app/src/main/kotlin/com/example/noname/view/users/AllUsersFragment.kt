package com.example.noname.view.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.FailureGetUsers
import com.example.domain.SuccessGetUsers
import com.example.domain.usecase.AddUserUseCase
import com.example.domain.usecase.GetUsersUseCase
import com.example.noname.adapter.UsersAdapter
import com.example.noname.databinding.FragmentAllUsersBinding
import com.example.noname.view.BaseFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AllUsersFragment : BaseFragment<FragmentAllUsersBinding>() {
    private lateinit var adapter: UsersAdapter

    private val addUser: AddUserUseCase by inject()
    private val getUsers: GetUsersUseCase by inject()

    override val binder: (LayoutInflater, ViewGroup?) -> FragmentAllUsersBinding =
        { layoutInflater, container ->
            FragmentAllUsersBinding.inflate(layoutInflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("edz", "AllUsersFragment. onViewCreated(). Thread: [${Thread.currentThread().name}]")
        lifecycleScope.launch {
            Log.d("edz", "AllUsersFragment. onViewCreated(). lifecycleScope. Thread: [${Thread.currentThread().name}]")
            binding.progressUsers.isVisible = true

            when (val usersLIst = getUsers()) {
                is SuccessGetUsers -> adapter = UsersAdapter(usersLIst.value) { user, isChecked ->
                    if (isChecked) {
                        lifecycleScope.launch {
                            addUser(user.copy(is_checked = true))
                        }
                    }
                }
                is FailureGetUsers -> binding.tvEmpty.isVisible = true
            }

            binding.progressUsers.isVisible = false

            binding.rvUsers.layoutManager = LinearLayoutManager(context)
            binding.rvUsers.adapter = adapter
        }
        Log.d("edz", "AllUsersFragment. onViewCreated().  2 Thread: [${Thread.currentThread().name}]")
    }
}