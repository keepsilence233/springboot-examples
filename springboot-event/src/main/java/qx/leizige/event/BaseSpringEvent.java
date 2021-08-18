package qx.leizige.event;

import org.springframework.context.ApplicationEvent;

/**
 * BaseSpringEvent
 *
 * @param <T> 事件类型
 */
public class BaseSpringEvent<T> extends ApplicationEvent {

    @Override
    public T getSource() {
        return (T) super.getSource();
    }

    public BaseSpringEvent(T source) {
        super(source);
    }
}
