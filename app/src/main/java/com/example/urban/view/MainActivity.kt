package com.example.urban.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urban.model.Thumb
import com.example.urban.di.Injection
import com.example.urban.R
import com.example.urban.model.ApiStatus
import com.example.urban.viewmodel.TermViewModel
import com.example.urban.viewmodel.TermViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: TermViewModel
    val injection = Injection()
    val termAdapter = TermAdapter()
    lateinit var searchTerm:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
                    TermViewModelFactory(injection.provideTermRepo())
        ).get(TermViewModel::class.java)

        rcycl_terms.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = termAdapter
        }

        createSortMenu()

        viewModel.termsLiveData.observeForever{
            termAdapter.terms = it
        }
        viewModel.statusLiveData.observeForever {
            if(it != ApiStatus.SUCCESS) errorDialog(it)
        }

        iv_search_icon.setOnClickListener {
            viewModel.getSearchTerm(et_search_bar.editableText.toString(),false)
        }
    }
    fun createSortMenu(){
        createSortButtonListener()
        initSortList()
        createSortListListener()
    }
    fun createSortButtonListener(){
        iv_sort_icon.setOnClickListener{
            lv_sort_options.apply{
                visibility = if(visibility == View.VISIBLE)
                    View.INVISIBLE
                else
                    View.VISIBLE
            }
        }
    }
    fun initSortList(){
        ArrayAdapter(
            this,
            R.layout.listview_menu,
            R.id.sort_menu_item,
            resources.getStringArray(R.array.thumb_sort_array)
        ).also {
                lv_sort_options.adapter = it
        }
    }
    fun createSortListListener(){
        lv_sort_options.setOnItemClickListener {
            _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            if(position == 0)
                viewModel.sortLiveData(Thumb.UP)
            else
                viewModel.sortLiveData(Thumb.DOWN)
            lv_sort_options.visibility = View.INVISIBLE
        }

    }
    fun errorDialog(status: ApiStatus){
        var title = ""
        var hasRetryButton = false

        when(status) {
            ApiStatus.NO_RESULTS ->
                title = getString(R.string.no_results)
            ApiStatus.NETWORK_FAIL->{
                title = getString(R.string.no_network_detected)
                hasRetryButton = true
            }
            else ->
                title = getString(R.string.retry_failed)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setTitle(title)
            .setNegativeButton(getString(R.string.close)) { dialog, _ ->
                dialog.cancel()
                dialog.dismiss()
            }

        if(hasRetryButton){
            alertDialogBuilder.setPositiveButton(R.string.retry){ _ , _ ->
                viewModel.getSearchTerm(et_search_bar.editableText.toString(),true)
            }
        }
        alertDialogBuilder.create().show()
    }
}
