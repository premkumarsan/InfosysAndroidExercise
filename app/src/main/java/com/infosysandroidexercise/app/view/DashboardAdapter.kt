package com.infosysandroidexercise.app.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.infosysandroidexercise.R
import com.infosysandroidexercise.app.model.RowModel
import com.infosysandroidexercise.databinding.DashboardSingleScreenBinding


class DashboardAdapter(private val data: ArrayList<RowModel>) :
    RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: DashboardSingleScreenBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding: DashboardSingleScreenBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.dashboard_single_screen,
            parent, false
        )

        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pojo = data[position]
        holder.binding.homeResponse = pojo
        holder.binding.executePendingBindings()
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}