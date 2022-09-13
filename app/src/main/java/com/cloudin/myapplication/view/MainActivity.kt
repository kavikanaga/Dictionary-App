package com.cloudin.myapplication.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.cloudin.myapplication.R
import com.cloudin.myapplication.model.WordModel
import com.example.myapplication.network.RetrofitInstance
import com.example.myapplication.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()
    private lateinit var apiInterface: RetrofitService
    private var word :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

        edt_word.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (edt_word.text.toString().isNotEmpty()) {
                    //Get the input value from edit text
                    word = v.text.toString()
                    //Call the API
                    apiCallToGetMeaning()
                    //Hide keyboard while click Done
                    hideKeyboard()
                    //Clear the focus from Edit text
                    edt_word.clearFocus()
                }
                true
            } else false
        }
    }

    //Method to hide the Keyboard
    private fun hideKeyboard(){
        val inputManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            this.currentFocus!!.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    //API method to get the Response
    private fun apiCallToGetMeaning(){
        recyclerview.alpha = 0.3f
        progressBar.visibility = View.VISIBLE
        disposables.add(
            apiInterface.getWordMeaning(
                word
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    if (response != null) {
                            tv_word.text = response[0].word
                            updateListView(response)
                    }
                }, { error ->
                    error.printStackTrace()
                    tv_word.text = getString(R.string.no_definition)
                    recyclerview.alpha = 1f
                    progressBar.visibility = View.GONE
                })
        )
    }

    //Update the List view based on the API response
    private fun updateListView(response: WordModel) {
        progressBar.visibility = View.GONE
        recyclerview.alpha = 1f
        val wordAdapter = WordAdapter(this, response[0].meanings)
        recyclerview.adapter = wordAdapter
    }
}