package cn.wengzi.eventbus.pub.impl;

import cn.wengzi.eventbus.event.Event;
import cn.wengzi.eventbus.message.BaseEventMessage;
import cn.wengzi.eventbus.pub.EventBusPublish;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventBusPublishImpl implements EventBusPublish<BaseEventMessage> {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private AsyncEventBus asyncEventBus;


    @Override
    public void post(Event<BaseEventMessage> event) {
        eventBus.post(event);
    }
}
