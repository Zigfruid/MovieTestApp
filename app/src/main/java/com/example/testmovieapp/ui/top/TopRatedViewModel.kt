package com.example.testmovieapp.ui.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmovieapp.data.ApiInterface
import com.example.testmovieapp.data.model.ResponseMovies
import com.example.testmovieapp.domain.MainRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TopRatedViewModel(private val mainRepository: MainRepository):ViewModel() {
    private var mutableSuccess: MutableLiveData<ResponseMovies> = MutableLiveData()
    val success: LiveData<ResponseMovies> = mutableSuccess
    private var mError: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = mError

    fun getTopRatedMovies(){
        mainRepository.getTopRatedMovies().onEach {
            it.onSuccess { res->
                mutableSuccess.value=res
            }
            it.onFailure { th->
                mError.value=th.message
            }
        }.catch() {
            mError.value = "catch error in viewModel"
        }.launchIn(viewModelScope)
    }

}