package cn.wengzi;

import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

public class Test {
    public static void main(String[] args) {

        EventBus eventBus = new EventBus();

        Subscribe subscribe = new Subscribe(eventBus);

        eventBus.post("666");
    }
}

@Slf4j
class Subscribe {
    EventBus eventBus;

    public Subscribe(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    @com.google.common.eventbus.Subscribe
    public void getEventMessage(String message) {
        log.info("message:{}", message);
    }


}
