package info.crazylab.lib.eventbus;

import rx.Observable;

public interface EventBus {

    void send(Event event);

    <T> Observable<T> observable(Class<T> eventClass);
}
