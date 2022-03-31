package com.kirilov.kontur_test.domain.interactor.base

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<TReturnType, in TParamsType> : UseCase() {

    internal abstract fun buildUseCaseSingle(params: TParamsType): Single<TReturnType>

    fun execute(
        params: TParamsType,
        onSuccess: (t: TReturnType) -> Unit,
        onError: (t: Throwable) -> Unit,
        onFinished: () -> Unit = {}
    ) {
        disposeLast()
        lastDisposable = buildUseCaseSingle(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)

        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}