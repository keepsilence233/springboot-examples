package cn.wengzi.eventbus.sub.impl;

import cn.wengzi.annoation.EventBusListener;
import cn.wengzi.eventbus.event.Event;
import cn.wengzi.eventbus.handler.SendTextEventHandler;
import cn.wengzi.eventbus.message.BaseEventMessage;
import cn.wengzi.eventbus.message.SendTextMessage;
import cn.wengzi.eventbus.sub.EventBusSubscriber;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EventBusListener
public class EventBusSubscriberImpl implements EventBusSubscriber<BaseEventMessage> {


    @Autowired
    private SendTextEventHandler sendTextEventHandler;

    /**
     * 所有订阅在这里处理
     *
     * @param event
     */
    @Subscribe
    @Override
    public void onReceiveMessage(Event<BaseEventMessage> event) {
        switch (event.getEventType()) {
            case SEND_TEXT_MESSAGE:
                sendTextEventHandler.handler((SendTextMessage) event.getMessage());
                break;
        }

    }
}
