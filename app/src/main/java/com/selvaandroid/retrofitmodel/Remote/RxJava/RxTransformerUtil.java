package com.selvaandroid.retrofitmodel.Remote.RxJava;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by ${Selva} on 09/08/19.
 */

public class RxTransformerUtil {

    private RxTransformerUtil() {
        throw new AssertionError();
    }

    public static <T> Single.Transformer<T, T> applySingleIoSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Single.Transformer<T, T> applySingleComputationSchedulers() {
        return observable -> observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    public static Completable.CompletableTransformer applyCompletableIoSchedulers() {
//        return completable -> completable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    /**
     * Observable transformer with execute given actions if observable didn't emit anything till given
     * time from subscription.
     */
    public static <T> Observable.Transformer<T, T> executeRunnableIfObservableDidntEmitUntilGivenTime(
            int time, TimeUnit timeUnit, Runnable runnable) {
        return observable ->
                observable.publish(publishedObservable -> publishedObservable.<T>timeout(time, timeUnit,
                        Observable.<T>create(subscriber -> runnable.run())
                                .ignoreElements()
                                .mergeWith(publishedObservable))
                );
    }

    public static <T> Observable.Transformer<T, T> androidComputation() {
        return observable -> observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}