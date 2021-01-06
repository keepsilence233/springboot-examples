package cn.wengzi.eventbus.config;

import cn.wengzi.annoation.EventBusListener;
import cn.wengzi.config.SpringUtil;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.Executors;

@Slf4j
@Configuration
public class EventBusConfig {

    @Bean
    AsyncEventBus asyncEventBus() {
        AsyncEventBus asyncEventBus = new AsyncEventBus("async", Executors.newSingleThreadExecutor());
        eventBusRegister(asyncEventBus);
        return asyncEventBus;
    }

    @Bean
    EventBus eventBus() {
        EventBus eventBus = new EventBus("sync");
        eventBusRegister(eventBus);
        return eventBus;
    }

    /**
     * 获取所有带有 @EventBusListener 的 bean,将他们注册为监听者
     *
     * @param eventBus
     */
    private void eventBusRegister(EventBus eventBus) {
        Reflections reflections = new Reflections("cn.wengzi");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(EventBusListener.class);
        if (!CollectionUtils.isEmpty(classSet)) {
            classSet.forEach(clazz -> {
                try {
                    eventBus.register(SpringUtil.getBean(clazz));
                } catch (Exception e) {
                    log.error("eventbus register error", e);
                }
            });
        }
    }

}
