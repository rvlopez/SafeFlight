package com.example.rviciana.safeflight.view.form.di

import com.example.rviciana.safeflight.view.form.FormActivity
import dagger.Subcomponent

@Subcomponent(modules = [FormModule::class])
interface FormComponent {

    fun inject(activity: FormActivity)
}