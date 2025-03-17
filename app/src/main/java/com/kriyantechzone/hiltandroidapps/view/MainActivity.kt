package com.kriyantechzone.hiltandroidapps.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kriyantechzone.hiltandroidapps.databinding.ActivityMainBinding
import com.kriyantechzone.hiltandroidapps.network.NetworkResultState
import com.kriyantechzone.hiltandroidapps.viewmodels.UserListViewModel
import com.kriyantechzone.hiltandroidapps.adapter.UsersListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModelUserList by viewModels<UserListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpUI()
        init()
    }

    private fun setUpUI() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    private fun init() {
        lifecycleScope.launch {
            viewModelUserList.uiState.collect {
                when (it) {
                    is NetworkResultState.Loading -> {
                        showLoading(true)
                    }

                    is NetworkResultState.Error -> {
                        showLoading(false)
                        Timber.tag("MainActivity").d("Failed to load Data::")
                        it.exception.printStackTrace()
                    }

                    is NetworkResultState.Success -> {
                        showLoading(false)

                        binding.maincontent.rvuserlist.adapter = UsersListAdapter(it.data) { user ->
                            Timber.tag("MainActivity").d(user.login)
                        }
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.maincontent.pbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}