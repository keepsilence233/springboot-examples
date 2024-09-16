package qx.leizige.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.BaseExecutionListener;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * 执行监听器{@link ExecutionListener}该监听器可以配置在节点和线,有以下三种类型
 * <p>
 * {@link BaseExecutionListener#EVENTNAME_START} 和 {@link BaseExecutionListener#EVENTNAME_END} 也适用于流程开始和结束节点
 * <p>
 * 流程实例启动时触发{@link BaseExecutionListener#EVENTNAME_START}事件
 * 完成节点后触发{@link BaseExecutionListener#EVENTNAME_END}事件
 * 经过连接线到下一节点会触发{@link BaseExecutionListener#EVENTNAME_TAKE}事件
 * </p>
 */
@Slf4j
//@Component
public class ManagerExecutionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {

        switch (execution.getEventName()) {
            case EVENTNAME_START:
                log.info(" start event");
                break;
            case EVENTNAME_END:
                log.info(" end event");
                break;
            case EVENTNAME_TAKE:
                log.info(" take event");
                break;
        }

    }
}
