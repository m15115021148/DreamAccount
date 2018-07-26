package com.romantic.dreamaccount.http.rxjava.observable;

import com.romantic.dreamaccount.http.result.HttpResponseException;
import com.romantic.dreamaccount.http.result.HttpResponseResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * <p>Description:数据类型转换
 *
 * <p>Created by Chenmeng on 2017/9/5.
 */

public class ResultTransformer {

    public static <T> ObservableTransformer<HttpResponseResult<T>, T> transformer() {
        return new ObservableTransformer<HttpResponseResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResponseResult<T>> upstream) {
                return upstream
                        .flatMap(ResultTransformer.<T>flatMap())
                        .compose(SchedulerTransformer.<T>transformer());
            }
        };
    }

    private static <T> Function<HttpResponseResult<T>, ObservableSource<T>> flatMap() {
        return new Function<HttpResponseResult<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(@NonNull final HttpResponseResult<T> tHttpResponseResult) throws Exception {
                return new Observable<T>() {
                    @Override
                    protected void subscribeActual(Observer<? super T> observer) {
                        if (tHttpResponseResult.isSuccess()) {
                            observer.onNext(tHttpResponseResult.getResult());
                            observer.onComplete();
                        } else {
                            observer.onError(new HttpResponseException(tHttpResponseResult.getMsg(), tHttpResponseResult.getState()));
                        }
                    }
                };
            }
        };
    }







}
