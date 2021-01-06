package cn.wengzi.eventbus.pub;

import cn.wengzi.eventbus.event.Event;

public interface EventBusPublish<T> {

    /**
     * 发布消息
     *
     * @param event
     */
    void post(Event<T> event);

}
