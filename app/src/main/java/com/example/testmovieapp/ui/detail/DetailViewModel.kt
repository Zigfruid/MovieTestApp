package com.example.testmovieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmovieapp.data.model.Credit
import com.example.testmovieapp.data.model.ResponseMovies
import com.example.testmovieapp.domain.MainRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailViewModel(private val mainRepository: MainRepository):ViewModel() {
    private var mutableSuccess: MutableLiveData<Credit> = MutableLiveData()
    val success: LiveData<Credit> = mutableSuccess
    private var mError: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = mError


    fun getMovieCredits(movieId:Int){
        mainRepository.getMovieCredits(movieId).onEach {
            it.onSuccess { m->
                mutableSuccess.value=m
            }
            it.onFailure { th->
                mError.value=th.localizedMessage
            }
        }.launchIn(viewModelScope)
    }

}