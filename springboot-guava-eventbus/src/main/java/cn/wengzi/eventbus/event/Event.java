package cn.wengzi.eventbus.event;

/**
 * 消息
 *
 * @param <T>
 */
public abstract class Event<T> {

    public abstract T getMessage();

    public abstract EventTypeEnum getEventType();
}
