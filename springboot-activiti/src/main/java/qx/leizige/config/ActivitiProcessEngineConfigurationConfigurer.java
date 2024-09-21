package qx.leizige.config;

import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import qx.leizige.listener.GlobalActivitiEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActivitiProcessEngineConfigurationConfigurer implements ProcessEngineConfigurationConfigurer {

    @Autowired
    private GlobalActivitiEventListener activitiEventListener;

    /**
     * Activiti 全局监听器 ${@link GlobalActivitiEventListener}
     *
     * @param configuration
     */
    @Override
    public void configure(SpringProcessEngineConfiguration configuration) {
        Map<String, List<ActivitiEventListener>> activitiEventListenerMap = new HashMap<>();
        // 设置全局监听器
        activitiEventListenerMap.put(ActivitiEventType.ACTIVITY_STARTED.name(), Lists.newArrayList(activitiEventListener));
        activitiEventListenerMap.put(ActivitiEventType.ACTIVITY_COMPLETED.name(), Lists.newArrayList(activitiEventListener));
        activitiEventListenerMap.put(ActivitiEventType.TASK_CREATED.name(), Lists.newArrayList(activitiEventListener));
        activitiEventListenerMap.put(ActivitiEventType.TASK_COMPLETED.name(), Lists.newArrayList(activitiEventListener));
        configuration.setTypedEventListeners(activitiEventListenerMap);
        configuration.setEventListeners(Lists.newArrayList(new GlobalActivitiEventListener()));
    }
}
