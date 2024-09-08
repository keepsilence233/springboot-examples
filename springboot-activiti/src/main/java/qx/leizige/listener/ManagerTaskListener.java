package qx.leizige.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 任务监听器{@link TaskListener}只能监听带有任务信息的节点,开始和结束节点在Activiti中没有任务信息无法监听
 * <p>
 *
 * </p>
 */
@Slf4j
//@Component
public class ManagerTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask task) {

        switch (task.getEventName()) {
            case EVENTNAME_CREATE:
                log.info(" create task event ");
                break;
            case EVENTNAME_ASSIGNMENT:
                log.info(" assignment task event");
                break;
            case EVENTNAME_COMPLETE:
                log.info(" complete task event");
                break;
            case EVENTNAME_DELETE:
                log.info(" delete task event");
                break;
        }
    }
}
