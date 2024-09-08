package qx.leizige.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.springframework.stereotype.Component;

@Component
public class GlobalActivitiEventListener implements ActivitiEventListener {

    @Override
    public void onEvent(ActivitiEvent event) {
        // 这里可以根据需要自行强转event的实现类，如获取流转实例对象可强转为ActivitiEntityEventImpl，如获取环节信息可强转为ActivitiActivityEventImpl，等等等
        switch (event.getType()) {
            case TASK_CREATED:
                System.out.println("TASK_CREATED event");
                break;
            case TASK_COMPLETED:
                System.out.println("TASK_COMPLETED event");
                break;
            case ACTIVITY_COMPLETED:
                System.out.println("ACTIVITY_COMPLETED event");
                break;
            case ACTIVITY_STARTED:
                System.out.println("ACTIVITY_STARTED event");
                break;
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
