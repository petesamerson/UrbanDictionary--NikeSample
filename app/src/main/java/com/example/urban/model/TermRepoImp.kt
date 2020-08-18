package com.example.urban.model

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TermRepoImp(private val termApiInterface: TermApiInterface): TermRepo {
   override fun getSearchTerm(term:String): Single<QueryResponse> {
       return termApiInterface.getTerm(term)
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
   }
}
