package com.example.noname.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.usecase.DeleteUserUseCase
import com.example.domain.usecase.GetSavedUsersUseCase
import com.example.noname.adapter.UsersAdapter
import com.example.noname.databinding.FragmentAllUsersBinding
import com.example.noname.ui.BaseFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SavedUsersFragment : BaseFragment<FragmentAllUsersBinding>() {
    private lateinit var adapter: UsersAdapter

    private val getSavedUsers: GetSavedUsersUseCase by inject()
    private val deleteUser: DeleteUserUseCase by inject()

    override val binder: (LayoutInflater, ViewGroup?) -> FragmentAllUsersBinding =
        { layoutInflater, container ->
            FragmentAllUsersBinding.inflate(layoutInflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UsersAdapter(emptyList()) { user, isChecked ->
            if (!isChecked) {
                lifecycleScope.launch {
                    deleteUser(user)
                }
            }
        }

        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.rvUsers.adapter = adapter
        lifecycleScope.launch {
            binding.progressUsers.isVisible = true

            getSavedUsers().collect {
                binding.progressUsers.isVisible = false

                binding.tvEmpty.isVisible = it.isEmpty()

                adapter.updateData(it)
            }
        }
    }
}