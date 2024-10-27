package com.example.budgetplanningapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetplanningapp.R
import com.example.budgetplanningapp.databinding.ActivityMainBinding
import com.example.budgetplanningapp.domain.models.DayItem
import com.example.budgetplanningapp.presentation.fragments.MainFragment
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModel
import com.example.budgetplanningapp.presentation.viewmodels.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DayAdapter
    private lateinit var model: MainViewModel
    private val dayItemList: ArrayList<DayItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction().replace(R.id.place_holder, MainFragment.newInstance())
            .commit()
        model = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]

    }
//    override fun onClick(item: DayItem) {
//
//}
    }




