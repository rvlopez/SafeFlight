package com.example.rviciana.safeflight.domain.usecase

import com.example.rviciana.safeflight.domain.repository.AirportsRepository
import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class AirportsUseCase @Inject constructor(private val airportsRepository: AirportsRepository,
                                          private val subscribeOn: Scheduler,
                                          private val observeOn: Scheduler
) {
    private var disposable: Disposable = Disposables.empty()

    fun execute(accessToken: String, limit: Int, offset: Int,
                onComplete: (AirportsResponse) -> Unit, onError: (Throwable) -> Unit) {
        disposable = airportsRepository.getAllAirports(accessToken, limit, offset)
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