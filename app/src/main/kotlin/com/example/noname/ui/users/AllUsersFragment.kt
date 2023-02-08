package com.example.noname.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.User
import com.example.noname.adapter.UsersAdapter
import com.example.noname.databinding.FragmentAllUsersBinding
import com.example.noname.ui.BaseFragment

class AllUsersFragment : BaseFragment<FragmentAllUsersBinding>(), AllUsersView {
    private lateinit var adapter: UsersAdapter
    private lateinit var presenter: AllUsersPresenter

    override val binder: (LayoutInflater, ViewGroup?) -> FragmentAllUsersBinding =
        { layoutInflater, container ->
            FragmentAllUsersBinding.inflate(layoutInflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = AllUsersPresenter(lifecycleOwner = viewLifecycleOwner, this)
        presenter.init()
    }

    override fun showMessage(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    override fun showUsers(users: List<User>) {
        adapter = UsersAdapter(users) { user, isChecked ->
            if (isChecked) {
                presenter.saveUser(user.copy(is_checked = true))
            }
        }

        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.rvUsers.adapter = adapter
    }

    override fun showError() {
        binding.tvEmpty.isVisible = true
    }

    override fun showProgress() {
        binding.progressUsers.isVisible = true
    }

    override fun hideProgress() {
        binding.progressUsers.isVisible = false
    }
}
