package qx.leizige.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ActivitiApplication.class})
@WebAppConfiguration
public class OALeave01Test {

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
                "/Users/chinese.youth/github/Repositories/springboot-examples/springboot-activiti/src/main/resources/processes/oa-leave-o1.bpmn20.xml";
        Deployment deployment = this.repositoryService.createDeployment()
                .addInputStream(processDefinitionFilePath,
                        Files.newInputStream(Paths.get(processDefinitionFilePath)))
                .name("OA流程-TEST-01")
                .deploy();
        System.out.println("部署流程成功：" + deployment);

        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .list();
        System.out.println("部署流程定义信息:" + processDefinitionList);
    }


    /**
     * 创建一个流程实例
     * <p>
     * 动态设置任务审批人 ${managerName}
     * <p>
     * 启动流程时，需要传所有流程节点中的占位符
     * </p>
     */
    @Test
    public void createProcessesInstance() {
        String processDefinitionId = "oa-leave-01:6:17503";

        //流程节点中变量，替换占位符
        Map<String, Object> variables = new HashMap<>();
        variables.put("managerName", "李四");

        //通过流程定义ID启动一个流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, variables);
        System.out.println("流程实例发起成功 : " + processInstance);
    }


    /**
     * 根据代办人查询任务
     */
    @Test
    public void queryTaskByAssignee() {
        String assignee = "李四";
        String processInstanceId = "35001";

        List<Task> taskList = taskService.createTaskQuery()
                //代办人姓名
                .taskAssignee(assignee)
                .processInstanceId(processInstanceId)
                //活动状态
                .active()
                .list();
        System.out.println("根据待办人查询任务 :" + taskList);
    }


    /**
     * 添加审批意见
     */
    @Test
    public void taskAddComment() {
        String assignee = "李四";
        String processInstanceId = "35001";

        Task task = taskService.createTaskQuery()
                //代办人姓名
                .taskAssignee(assignee)
                .processInstanceId(processInstanceId)
                //活动状态
                .active()
                .singleResult();
        if (Objects.nonNull(task)) {
            taskService.addComment(task.getId(), processInstanceId, "审批意见:审批通过");
            System.out.println("审批通过并且添加审批意见成功");
        }
    }

    /**
     * 查询审批意见
     */
    @Test
    public void queryTaskComment() {
        String assignee = "李四";
        String processInstanceId = "35001";

        Task task = taskService.createTaskQuery()
                //代办人姓名
                .taskAssignee(assignee)
                .processInstanceId(processInstanceId)
                //活动状态
                .active()
                .singleResult();
        List<Comment> taskCommentList = taskService.getTaskComments(task.getId());
        System.out.println("个人审批意见:" + taskCommentList);
    }
}
