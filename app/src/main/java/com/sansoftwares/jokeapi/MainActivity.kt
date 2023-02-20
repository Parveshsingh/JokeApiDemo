package com.sansoftwares.jokeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sansoftwares.jokeapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var mainHandler : Handler
    private lateinit var adapter : RecyclerViewAdapter
    private lateinit var manager : RecyclerView.LayoutManager
    private lateinit var arrayAdapter: ArrayAdapter<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainHandler = Handler(Looper.getMainLooper())
        manager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this, MyViewModelFactory()).get(MainViewModel::class.java)

        viewModel.jokelist.observe(this, Observer {
//            arrayAdapter = ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, it)
//            binding.rvLayoutDetail.adapter = arrayAdapter
//            adapter = RecyclerViewAdapter(it)
//            binding.rvLayoutDetail.adapter = adapter;
//            binding.rvLayoutDetail.layoutManager = manager;

        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getData()
        mainHandler.post(object : Runnable {
            override fun run() {
                viewModel.jokelist.observe(this@MainActivity, Observer {
                    Log.d(TAG, "onCreate: $it")
                    adapter = RecyclerViewAdapter(it)
                    binding.rvLayoutDetail.adapter = adapter;
                    binding.rvLayoutDetail.layoutManager = manager;

                })
                viewModel.errorMessage.observe(this@MainActivity, Observer {
                })
                mainHandler.postDelayed(this, 60000)
//                mainHandler.postDelayed(this, 60000)

                viewModel.getData()
                Toast.makeText(this@MainActivity, "Joke Changed", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onResume() {
        super.onResume()
        Log.d("resume", "On Resume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Restart","App is Restarted")
        viewModel.getData()
            viewModel.jokelist.postValue(listOf());
            viewModel.alljokelist.observe(this@MainActivity, Observer {
            Log.d("On Resume Data", it.toString())
            adapter = RecyclerViewAdapter(it)
            binding.rvLayoutDetail.adapter = adapter;
            binding.rvLayoutDetail.layoutManager = manager;

        })
        viewModel.errorMessage.observe(this@MainActivity, Observer {
        })
    }
}