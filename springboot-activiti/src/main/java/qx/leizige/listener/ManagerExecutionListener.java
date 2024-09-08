package qx.leizige.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * 执行监听器{@link ExecutionListener}该监听器可以配置在节点和线,有以下三种类型
 * <p>
 * start:开始时触发
 * end:结束时触发
 * take:用于监控流程线,当流程流转该线时触发
 * </p>
 */
@Slf4j
//@Component
public class ManagerExecutionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        log.info("\r\n *****************MangerExecutionListener流程监听器*****************" +
                        "\r\n execution.getCurrentFlowElement().getId()：【{}】," +
                        "\r\n execution.getCurrentFlowElement().getName():【{}】，" +
                        "\r\n execution.getEventName()：【{}】，" +
                        "\r\n execution.getProcessDefinitionId()：【{}】，" +
                        "\r\n execution.getProcessInstanceId()：【{}】，" +
                        "\r\n execution：【{}】",
                execution.getCurrentFlowElement().getId(),
                execution.getCurrentFlowElement().getName(),
                execution.getEventName(),
                execution.getProcessDefinitionId(),
                execution.getProcessInstanceId(),
                execution);

        /**
         * {@link EVENTNAME_START} 和 {@link EVENTNAME_END} 也适用于流程开始和结束节点,
         * 流程实例启动时触发{@link EVENTNAME_START}事件
         * 完成节点后触发{@link EVENTNAME_END}事件
         * 经过连接线到下一节点会触发{@link EVENTNAME_TAKE}事件
         */
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
