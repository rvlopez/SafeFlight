package com.example.rviciana.safeflight.view.oauth.di

import com.example.rviciana.safeflight.view.oauth.OAuthActivity
import dagger.Subcomponent

@Subcomponent(modules = [OAuthModule::class])
interface OAuthComponent {

    fun inject(activity: OAuthActivity)
}