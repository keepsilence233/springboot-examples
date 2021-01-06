package cn.wengzi.eventbus.handler;

/**
 * 事件处理
 *
 * @param <T>
 */
public abstract class BaseEventHandler<T> {

    public abstract void handler(T message);
}
