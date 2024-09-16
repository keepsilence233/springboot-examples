package qx.leizige.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 任务监听器{@link TaskListener}只能监听带有任务信息的节点,开始和结束节点在Activiti中没有任务信息无法监听。需要在userTask标签中配置
 * <p>
 * create: 任务被创建，并且所有的属性都被设置好后。
 * <p>
 * assignment: 任务被委派给某人后.。注意: 当流程执行到达一个userTask时，会先触发一个assignment事件，再触发create事件。
 * <p>
 * complete:在任务完成后，且被从运行时数据（runtime data）中删除前触发。
 * <p>
 * delete: 在任务将要被删除之前发生。注意，当任务通过completeTask完成任务时，它也会被执行
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
