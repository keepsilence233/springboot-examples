package cn.wengzi.eventbus.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 事件类型
 */
@Getter
@AllArgsConstructor
public enum EventTypeEnum {

    SEND_TEXT_MESSAGE(1, "发送短信");

    private final Integer code;
    private final String desc;

}
