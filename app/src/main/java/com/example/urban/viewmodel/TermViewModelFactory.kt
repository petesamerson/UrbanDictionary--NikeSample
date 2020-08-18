package com.example.urban.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.urban.model.TermRepo

class TermViewModelFactory(private val termRepo: TermRepo) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TermViewModel(termRepo) as T
    }
}