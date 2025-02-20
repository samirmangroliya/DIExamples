package com.kriyantechzone.hiltandroidapps.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kriyantechzone.hiltandroidapps.databinding.ItemUsersListBinding
import com.kriyantechzone.hiltandroidapps.network.model.User
import javax.inject.Inject

class UsersListAdapter @Inject constructor(private val data:List<User>, val onItemClick:(User)->Unit) :
    RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, onItemClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder private constructor(private val binding: ItemUsersListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onItemClick: (User) -> Unit) {
            binding.user = user
            itemView.setOnClickListener { onItemClick(user) }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUsersListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}