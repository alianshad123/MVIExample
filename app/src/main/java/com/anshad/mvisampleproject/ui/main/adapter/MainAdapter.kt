package com.anshad.mvisampleproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anshad.mvisampleproject.data.model.User
import com.anshad.mvisampleproject.databinding.ItemLayoutBinding


class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    private lateinit var binding: ItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usersData = users[position]
        holder.binder.users=usersData
    }

    override fun getItemCount(): Int = users.size

    fun addData(list: List<User>) {
        users.addAll(list)
    }

    class ViewHolder(val binder:ItemLayoutBinding): RecyclerView.ViewHolder(binder.root)

}

