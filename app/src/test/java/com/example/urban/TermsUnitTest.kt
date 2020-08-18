package com.example.urban

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.urban.model.ApiStatus
import com.example.urban.model.QueryResponse
import com.example.urban.model.Term
import com.example.urban.model.TermRepo
import com.example.urban.viewmodel.TermViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TermsUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val termRepo = mock<TermRepo>()
    val observerStatus = mock<Observer<ApiStatus>>()
    val observerList = mock<Observer<List<Term>>>()

    val viewmodel by lazy { TermViewModel(termRepo) }

    @Before
    fun initTest() {
        Mockito.reset(termRepo, observerStatus)
    }

    @Test
    fun `queryTermThatExists_getSearchTerm`(){
        val response = QueryResponse(listOf(
            Term("term","def",0,0)))
        whenever(termRepo.getSearchTerm(ArgumentMatchers.anyString()))
            .thenReturn(Single.just(response))
        viewmodel.statusLiveData.observeForever(observerStatus)
        viewmodel.termsLiveData.observeForever(observerList)

        viewmodel.getSearchTerm("term",false)
        assertEquals(viewmodel.statusLiveData.value,ApiStatus.SUCCESS)
        assertEquals(viewmodel.termsLiveData.value?.size,1)
    }
}
