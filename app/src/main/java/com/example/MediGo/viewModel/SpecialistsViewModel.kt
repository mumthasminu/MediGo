package com.example.MediGo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.MediGo.model.Local.ServiceCategory
import com.example.MediGo.model.Local.Specialists

class SpecialistsViewModel : ViewModel() {
    private val _specialists = MutableLiveData<Specialists>()
    val specialists: LiveData<Specialists> get() = _specialists

    fun setServiceCategory(specialist: Specialists) {
        _specialists.value = specialist
    }
}