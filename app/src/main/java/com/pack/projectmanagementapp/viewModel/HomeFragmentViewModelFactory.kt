package com.pack.projectmanagementapp.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pack.projectmanagementapp.services.repo.ProductNetworkRepository
import com.pack.projectmanagementapp.viewModel.HomeFragmentViewModel

class HomeFragmentViewModelFactory(
    private val application: Application
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}