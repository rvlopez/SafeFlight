package com.example.rviciana.safeflight.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeInjector()

        val layoutResourceId = getLayoutResourceId()
        if (layoutResourceId > 0) {
            setContentView(layoutResourceId)
        }

        initializePresenter()
    }

    abstract fun initializeInjector()

    abstract fun initializePresenter()

    abstract fun getLayoutResourceId() : Int
}