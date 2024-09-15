package qx.leizige.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
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
 * </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ActivitiApplication.class})
@WebAppConfiguration
public class MultiInstanceForUserTaskSequentialTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;


    /***
     * 部署流程
     */
    @Test
    public void deployProcessDefinition() throws IOException {
        String processDefinitionFilePath =
                "/Users/chinese.youth/github/Repositories/springboot-examples/springboot-activiti/src/main/resources/processes/test-multi-instance-for-user-task-sequential.bpmn20.xml";
        Deployment deployment = this.repositoryService.createDeployment()
                .addInputStream(processDefinitionFilePath,
                        Files.newInputStream(Paths.get(processDefinitionFilePath)))
                .name("多实例顺序方式办理")
                .deploy();
        System.out.println("部署流程成功：" + deployment);

        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .list();
        System.out.println("部署流程定义信息:" + processDefinitionList);
        //部署流程定义信息:[ProcessDefinitionEntity[test-multi-instance-for-user-task-sequential:2:60012]]
    }


    /**
     * 测试多实例会签按照顺序执行
     */
    @Test
    public void testProcess() {
        String processDefinitionId = "test-multi-instance-for-user-task-sequential:2:60012";

        //设置会签的三个用户
        ArrayList<String> users = Lists.newArrayList("zs", "ls", "ww");

        //流程节点中变量，替换占位符
        Map<String, Object> variables = new HashMap<>();
        variables.put("users", users);

        //启动流程
        runtimeService.startProcessInstanceById(processDefinitionId, variables);

        //查询用户任务 必需按照顺序 zs --> ls --> ww 的顺序
        //如果直接查询ls的任务是获取不到的
        taskService.createTaskQuery().taskAssignee("ls").singleResult(); // result task is null
        users.forEach(user -> {
            Task task = taskService.createTaskQuery().taskAssignee(user).singleResult();
            System.out.println("用户:" + user + "的任务信息 : " + task);
            taskService.complete(task.getId());
        });

    }

}
