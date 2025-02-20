package com.kriyantechzone.hiltandroidapps.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriyantechzone.hiltandroidapps.network.model.User
import com.kriyantechzone.hiltandroidapps.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val userListRepository: UserListRepository) :
    ViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>>  get() = _users

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _users.value = userListRepository.getUserList()
        }
    }

}