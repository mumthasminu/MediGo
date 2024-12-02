package com.example.MediGo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.MediGo.model.Local.ServiceCategory

class ServiceDetailsViewModel : ViewModel() {
    private val _serviceCategory = MutableLiveData<ServiceCategory>()
    val serviceCategory: LiveData<ServiceCategory> get() = _serviceCategory

    fun setServiceCategory(category: ServiceCategory) {
        _serviceCategory.value = category
    }
}