package cn.wengzi.eventbus.handler;

import cn.wengzi.eventbus.message.SendTextMessage;
import cn.wengzi.model.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendTextEventHandler extends BaseEventHandler<SendTextMessage> {


    @Override
    public void handler(SendTextMessage message) {
        TextMessage textMessage = message.getTextMessage();
        log.info("======发送短信,消息:{},手机号:{}======", textMessage.getMessage(), textMessage.getMobile());
    }
}
