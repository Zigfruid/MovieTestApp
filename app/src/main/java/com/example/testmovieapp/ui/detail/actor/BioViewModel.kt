package com.example.testmovieapp.ui.detail.actor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmovieapp.data.model.BiographyOfPerson
import com.example.testmovieapp.data.model.Compilation
import com.example.testmovieapp.data.model.Credit
import com.example.testmovieapp.domain.MainRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BioViewModel(private val mainRepository: MainRepository):ViewModel() {

    private var mutableSuccess: MutableLiveData<BiographyOfPerson> = MutableLiveData()
    val success: LiveData<BiographyOfPerson> = mutableSuccess
    private var mError: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = mError

    private var mutableSuccessMovie: MutableLiveData<Compilation> = MutableLiveData()
    val successMovie: LiveData<Compilation> = mutableSuccessMovie


    fun getBioCredits(actorId:Int){
        mainRepository.getBio(actorId).onEach {
            it.onSuccess { m->
                mutableSuccess.value=m
            }
            it.onFailure { th->
                mError.value=th.localizedMessage
            }
        }.launchIn(viewModelScope)
    }


    fun getMovieCredits(actorId:Int){
        mainRepository.getMovies(actorId).onEach {
            it.onSuccess { m->
                mutableSuccessMovie.value=m
            }
            it.onFailure { th->
                mError.value=th.localizedMessage
            }
        }.launchIn(viewModelScope)
    }



}