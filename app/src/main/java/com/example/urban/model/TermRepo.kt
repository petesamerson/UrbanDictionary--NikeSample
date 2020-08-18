package com.example.urban.model

import io.reactivex.Single

interface TermRepo {
    fun getSearchTerm(term:String): Single<QueryResponse>
}