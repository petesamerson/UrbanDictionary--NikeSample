package com.example.urban.di

import com.example.urban.model.TermApiInterface
import com.example.urban.model.TermRepo
import com.example.urban.model.TermRepoImp

class Injection {
    private var termApiInterface: TermApiInterface? = null
    fun provideTermRepo(): TermRepo {
        return TermRepoImp(provideApiInterface())
    }
    private fun provideApiInterface(): TermApiInterface {
        if(termApiInterface == null){
            termApiInterface = TermApiInterface.instance
        }
        return termApiInterface as TermApiInterface
    }
}