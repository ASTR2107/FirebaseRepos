package com.example.firebaseauthentification.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthentification.domain.model.model.data.PostResponse
import com.example.firebaseauthentification.use_case.login.GetPost.AndPost
import com.example.firebaseauthentification.use_case.login.GetPost.DeletePostUseCase
import com.example.firebaseauthentification.use_case.login.GetPost.GetPost
import com.example.firebaseauthentification.use_case.login.GetPost.PatchPostUseCase
import com.example.firebaseauthentification.use_case.login.GetPost.PutPostUseCase
import com.example.firebaseauthentification.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val andPost: AndPost,
    private val deletePostUseCase: DeletePostUseCase,
    private val getPost: GetPost,
    private val patchPostUseCase: PatchPostUseCase,
    private val putPostUseCase: PutPostUseCase
): ViewModel() {
    private val _allPostResponse = MutableLiveData<NetworkResult<List<PostResponse>>>()
    val allPostResponse: LiveData<NetworkResult<List<PostResponse>>>
        get() = _allPostResponse

    init {
        getAllPost()
    }

    private fun getAllPost() {
        viewModelScope.launch {
            getPost.execute().let {
                _allPostResponse.value = it
            }

        }

    }

    fun andPost() {
        viewModelScope.launch {
            andPost.execute(body = PostResponse(title = "test", body = "body"))
        }
    }

    fun putPost() {
        viewModelScope.launch {
            putPostUseCase.execute(id = "1",body = PostResponse(title = "Test title", body = "Test body")).let {
                Log.d("checkedMe", "data: ${it.data}")
            }
        }
    }

    fun patchPost() {
        viewModelScope.launch {
            patchPostUseCase.execute(id = "1",body = PostResponse(title = "Test title", body = "Test body") ).let {
                Log.d("checkedMe", "data: ${it.data}")
            }
        }
        fun deletePost() {
            viewModelScope.launch {
                deletePostUseCase.execute(id = "1").let {
                    Log.d("checkedMe", "data: ${it.data}")
                }
            }
        }
    }
}