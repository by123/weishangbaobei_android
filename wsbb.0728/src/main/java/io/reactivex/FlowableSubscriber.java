package io.reactivex;

import io.reactivex.annotations.NonNull;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public interface FlowableSubscriber<T> extends Subscriber<T> {
    void onSubscribe(@NonNull Subscription subscription);
}
