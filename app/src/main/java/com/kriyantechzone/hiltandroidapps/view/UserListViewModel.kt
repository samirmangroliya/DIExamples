package com.kriyantechzone.hiltandroidapps.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kriyantechzone.hiltandroidapps.MainActivity
import com.kriyantechzone.hiltandroidapps.network.NetworkResultState
import com.kriyantechzone.hiltandroidapps.network.model.User
import com.kriyantechzone.hiltandroidapps.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(private val userListRepository: UserListRepository) :
    ViewModel() {

    private val _uiState =
        MutableStateFlow<NetworkResultState<List<User>>>(NetworkResultState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getUserData()
    }
    private fun getUserData() {
        viewModelScope.launch {
            try {
                val userList = userListRepository.getUserList()
                _uiState.value = NetworkResultState.Success(userList)
            } catch (e: Exception) {
                _uiState.value = NetworkResultState.Error(e.message ?: "", e)
            }
        }
    }

}