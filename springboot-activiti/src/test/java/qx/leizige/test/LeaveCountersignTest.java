package qx.leizige.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import qx.leizige.ActivitiApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * <p>
 * 可以在任务监听类中获取内置变量以便协助处理业务逻辑,内置变量有:
 * <p>
 * nrOfinstances, nrOfActivenstances, nrOfCompletedInstances, loopCounter
 * </p>
 * <p>
 * nrOfInstances：实例总数
 * <p>
 * nrOfActiveInstances：当前活动的，也就是说未完成的，实例数量。对于顺序多实例，这个值总为1.
 * <p>
 * nrOfCompletedInstances：已经完成的实例数量
 * </p>
 * </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ActivitiApplication.class})
@WebAppConfiguration
public class MultiInstanceForUserTaskNoSequentialTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;


    /**
     * 测试多实例会签按照并行执行
     */
    @Test
    public void testProcess() {
        String processDefinitionKey = "test-multi-instance-for-user-task-no-sequential";

        //设置会签的三个用户
        ArrayList<String> users = Lists.newArrayList("zs1", "ls1", "ww1");

        //流程节点中变量，替换占位符
        Map<String, Object> variables = new HashMap<>();
        variables.put("users", users);

        //启动流程
        runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);

        //查询用户任务 不用必需按照顺序 zs --> ls --> ww 的顺序,三个用户可以同时处理任务

        Task task = taskService.createTaskQuery().taskAssignee("ww1").singleResult();
        System.out.println("用户:" + "ww1" + "的任务信息 : " + task);
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().taskAssignee("zs1").singleResult();
        System.out.println("用户:" + "zs1" + "的任务信息 : " + task);
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().taskAssignee("ls1").singleResult();
        System.out.println("用户:" + "ls1" + "的任务信息 : " + task);
        taskService.complete(task.getId());

    }

    /**
     * 测试多实例会签按照并行执行。按照结束条件提前结束当前实例
     */
    @Test
    public void testProcessWithCompletionCondition() {
        String processDefinitionKey = "test-multi-instance-for-user-task-no-sequential";

        //设置会签的三个用户
        ArrayList<String> users = Lists.newArrayList("zs2", "ls2", "ww2");

        //流程节点中变量，替换占位符
        Map<String, Object> variables = new HashMap<>();
        variables.put("users", users);
        variables.put("rate", 0.6d);

        //启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);

        //查询用户任务 不用必需按照顺序 zs --> ls --> ww 的顺序,三个用户可以同时处理任务

        Task task = taskService.createTaskQuery().taskAssignee("ww2").singleResult();
        System.out.println("用户:" + "ww2" + "的任务信息 : " + task);
        if (Objects.nonNull(task)) taskService.complete(task.getId());

        task = taskService.createTaskQuery().taskAssignee("zs2").singleResult();
        System.out.println("用户:" + "zs2" + "的任务信息 : " + task);
        if (Objects.nonNull(task)) taskService.complete(task.getId());


        //前面两个任务执行完成后已经结束当前多实例节点,下面这个任务的查询结果 task is null
        task = taskService.createTaskQuery().taskAssignee("ls2").singleResult();
        System.out.println("用户:" + "ls2" + "的任务信息 : " + task);
        if (Objects.nonNull(task)) taskService.complete(task.getId());

    }

}
