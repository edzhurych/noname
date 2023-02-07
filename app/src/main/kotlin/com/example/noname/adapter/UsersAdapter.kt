package com.example.noname.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.User
import com.example.noname.databinding.UserItemBinding
import com.squareup.picasso.Picasso

class UsersAdapter(
    private var list: List<User>,
    private val checkBoxListener: (User, Boolean) -> Unit
) : RecyclerView.Adapter<UsersAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UserHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.tvUserName.text = login
                Picasso.get().load(avatar_url).into(binding.imageAvatar)

                binding.checkboxUserSaved.isChecked = is_checked ?: false
                binding.checkboxUserSaved.setOnCheckedChangeListener { _, isChecked ->
                    checkBoxListener(this, isChecked)
                }
            }
        }
    }

    fun updateData(updatedList: List<User>) {
        list = updatedList
        notifyDataSetChanged()
    }

    inner class UserHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)
}