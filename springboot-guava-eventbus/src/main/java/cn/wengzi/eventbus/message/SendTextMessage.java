package cn.wengzi.eventbus.message;

import cn.wengzi.model.TextMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SendTextMessage extends BaseEventMessage {

    private TextMessage textMessage;

}
