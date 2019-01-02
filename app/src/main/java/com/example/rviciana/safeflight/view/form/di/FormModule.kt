package com.example.rviciana.safeflight.view.form.di

import com.example.rviciana.safeflight.view.form.FormContract
import com.example.rviciana.safeflight.view.form.FormPresenter
import dagger.Module
import dagger.Provides

@Module
class FormModule {

    @Provides
    fun provideFormPresenter(): FormContract.Presenter = FormPresenter()

}