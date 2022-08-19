package com.anshad.mvisampleproject


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anshad.mvisampleproject.data.api.ApiHelperImpl
import com.anshad.mvisampleproject.data.api.RetrofitBuilder
import com.anshad.mvisampleproject.data.model.User
import com.anshad.mvisampleproject.databinding.ActivityMainBinding
import com.anshad.mvisampleproject.ui.main.adapter.MainAdapter
import com.anshad.mvisampleproject.ui.main.intent.MainIntent
import com.anshad.mvisampleproject.ui.main.viewmodel.MainViewModel
import com.anshad.mvisampleproject.ui.main.viewstate.MainState
import com.anshad.mvisampleproject.util.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    // Initialize variables
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        binding?.buttonFetchUser?.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }


    private fun setupUI() {
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.run {
            addItemDecoration(
                DividerItemDecoration(
                    binding?.recyclerView?.context,
                    (binding?.recyclerView?.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        binding?.recyclerView?.adapter = adapter
    }


    private fun setupViewModel() {

        val viewModelFactory = ViewModelFactory(ApiHelperImpl(
            RetrofitBuilder.apiService
        ))
        mainViewModel= ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)

       /* mainViewModel = ViewModelProvider.(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(MainViewModel::class.java)*/
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        binding?.buttonFetchUser?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.buttonFetchUser?.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.buttonFetchUser?.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        binding?.recyclerView?.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }
}