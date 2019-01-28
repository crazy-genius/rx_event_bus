package info.crazylab.lib.eventbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

import java.util.Objects;

public class EventBusImplementation implements EventBus {

    private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    @Override
    public void send(Event event) {
        if (this.bus.hasObservers()) {
            this.bus.onNext(event);
        }
    }

    @Override
    public <T> Observable<T> observable(Class<T> eventClass) {
        return this.bus
                .filter(Objects::nonNull)
                .filter(eventClass::isInstance)
                .cast(eventClass)
                ;
    }
}
