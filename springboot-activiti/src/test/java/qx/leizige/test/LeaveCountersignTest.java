package qx.leizige.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import qx.leizige.ActivitiApplication;
import qx.leizige.listener.GlobalActivitiEventListener;
import qx.leizige.listener.ManagerExecutionListener;
import qx.leizige.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 请假流程 -- 会签 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ActivitiApplication.class})
@WebAppConfiguration
public class LeaveCountersignTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;


    @Test
    public void testProcess() {
        String processDefinitionKey = "leave-countersign";

        ArrayList<String> users = Lists.newArrayList("hr1", "hr2");

        //流程节点中变量，替换占位符
        Map<String, Object> variables = new HashMap<>();
        variables.put("processSponsor", "张三");
        variables.put("users", users);

        //启动流程
        runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);

        //查询上级领导任务
        completeTask().accept("TOM23333");

        //查询HR部门任务
        users.forEach(completeTask());

    }

    public Consumer<String> completeTask() {
        return (assignee) -> {
            Task task = taskService.createTaskQuery()
                    .taskAssignee(assignee)
                    .active().singleResult();
            if (Objects.nonNull(task)) {
                taskService.complete(task.getId());
            }
        };
    }


}
