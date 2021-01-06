package cn.wengzi.eventbus.sub;

import cn.wengzi.eventbus.event.Event;

public interface EventBusSubscriber<T> {

    /**
     * 处理事件消息
     *
     * @param event
     */
    void onReceiveMessage(Event<T> event);
}
