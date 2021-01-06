package cn.wengzi.controller;

import cn.wengzi.eventbus.event.Event;
import cn.wengzi.eventbus.event.EventTypeEnum;
import cn.wengzi.eventbus.message.BaseEventMessage;
import cn.wengzi.eventbus.message.SendTextMessage;
import cn.wengzi.eventbus.pub.EventBusPublish;
import cn.wengzi.model.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextMessageController {


    @Autowired
    private EventBusPublish<BaseEventMessage> publish;


    @GetMapping(value = "/textMessage")
    public void sendTextMessage() {
        publish.post(new Event<BaseEventMessage>() {
            @Override
            public BaseEventMessage getMessage() {
                TextMessage message = TextMessage.builder()
                        .message("短信666").mobile("1008611").build();
                SendTextMessage sendTextMessage = new SendTextMessage();
                sendTextMessage.setTextMessage(message);
                return sendTextMessage;
            }

            @Override
            public EventTypeEnum getEventType() {
                return EventTypeEnum.SEND_TEXT_MESSAGE;
            }
        });
    }
}
