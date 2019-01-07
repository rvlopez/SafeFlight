package com.example.rviciana.safeflight.domain.usecase

import com.example.rviciana.safeflight.data.NetworkConfig
import com.example.rviciana.safeflight.data.dto.OAuthData
import com.example.rviciana.safeflight.domain.repository.OAuthRepository
import com.example.rviciana.safeflight.domain.bo.OAuthResponse
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class OAuthUseCase @Inject constructor(private val oauthRepository: OAuthRepository,
                                       private val subscribeOn: Scheduler,
                                       private val observeOn: Scheduler
) {

    private var disposable: Disposable = Disposables.empty()

    fun execute(onComplete: (OAuthResponse) -> Unit, onError: (Throwable) -> Unit) {
        val oAuthData = OAuthData(
                NetworkConfig.API_KEY,
                NetworkConfig.API_SECRET,
                NetworkConfig.CLIENT_CREDENTIALS)

        disposable = oauthRepository.authCredentials(oAuthData)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(onComplete, onError)
    }

    fun dispose() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}