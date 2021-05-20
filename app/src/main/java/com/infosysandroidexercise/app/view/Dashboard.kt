package com.infosysandroidexercise.app.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.infosysandroidexercise.app.model.ResponseModel
import com.infosysandroidexercise.app.model.RowModel
import com.infosysandroidexercise.app.viewmodel.DashboardViewModel
import com.infosysandroidexercise.app.viewmodel.DashboardViewModelFactory
import com.infosysandroidexercise.databinding.ActivityMainBinding


class Dashboard : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels { DashboardViewModelFactory() }

    private var adapter: DashboardAdapter? = null
    private var responseList: ArrayList<RowModel> = ArrayList()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {

        /*
         * Initializing the Adapter
         */
        adapter = DashboardAdapter(responseList)
        binding.adapter = adapter
        binding.dataAvailable = true
        binding.showProgress = false


        /*
         * Retrieving response from the LiveData
         */
        viewModel.responseLiveData.observe(this, {

            if (it != null && it is ResponseModel) {
                responseList.clear()
                binding.title.text = it.title
                it.rows.let { it1 -> responseList.addAll(it1) }
                binding.adapter?.notifyDataSetChanged()
                binding.dataAvailable = it.rows.isNotEmpty()
            } else if (it != null && it is String) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            } else {
                binding.dataAvailable = false
            }
        })

        viewModel.progressLiveData.observe(this, {
            binding.showProgress = it
        })

    }
}