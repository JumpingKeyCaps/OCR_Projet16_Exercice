package com.openclassrooms.arista.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(private val getUserUsecase: GetUserUsecase) :
    ViewModel() {
    private val _userFlow = MutableStateFlow<User?>(null)
    val userFlow: StateFlow<User?> = _userFlow.asStateFlow()

    init {
        loadUserData()
    }

   private fun loadUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUsecase.execute()
            _userFlow.value = user
        }
    }
}
