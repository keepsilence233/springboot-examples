package qx.leizige.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
import java.util.List;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ActivitiApplication.class})
@WebAppConfiguration
public class ActivitiProcessesTest {

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
                "/Users/chinese.youth/github/Repositories/springboot-examples/springboot-activiti/src/main/resources/processes/oa-leave.bpmn20.xml";
        Deployment deploy = this.repositoryService.createDeployment()
                .addInputStream(processDefinitionFilePath,
                        Files.newInputStream(Paths.get(processDefinitionFilePath)))
                .name("OA流程--请假审批")
                .deploy();
        System.out.println("部署流程定义成功：" + deploy);
    }


    /**
     * 查询流程部署信息
     */
    @Test
    public void queryDeploymentInfo() {
        List<Deployment> deploymentList = repositoryService.createDeploymentQuery().list();

        DeploymentQuery deploymentById = repositoryService.createDeploymentQuery().deploymentId("2501");

        System.out.println("流程部署信息 = " + deploymentList.toString());
    }


    /**
     * 查询流程定义信息
     */
    @Test
    public void queryProcessesInfo() {
        List<ProcessDefinition> processDefinitionList = repositoryService
                .createProcessDefinitionQuery()
                .list();
        System.out.println("查询流程定义信息 = " + processDefinitionList);
    }


    /**
     * 根据流程部署id删除 deployment
     */
    @Test
    public void deleteDeploymentById() {
        List<Deployment> deploymentList = repositoryService.createDeploymentQuery()
                .deploymentId("").list();
        if (Objects.nonNull(deploymentList) && !deploymentList.isEmpty()) {
            repositoryService.deleteDeployment("");
        }
    }


    /**
     * 根据流程定义ID发起一个{oa-leave}审批流程实例
     */
    @Test
    public void startProcesses() {

        String processDefinitionId = "oa-leave:2:2503"; //流程定义ID

        List<Deployment> deploymentList = repositoryService.createDeploymentQuery()
                .processDefinitionKey("oa-leave")
                .list();
        if (Objects.nonNull(deploymentList) && !deploymentList.isEmpty()) {
            ProcessInstance processInstance
                    = runtimeService.startProcessInstanceById(processDefinitionId);//通过流程定义ID启动一个流程实例
            System.out.println("流程实例发起成功 : " + processInstance);
        }
    }


    /**
     * 完成任务
     */
    @Test
    public void completeTask() {
        String processInstanceId = "5001";   //流程实例ID

        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .list();
        if (Objects.nonNull(taskList) && !taskList.isEmpty()) {
            taskService.complete(taskList.get(0).getId());  //根据任务ID 完成任务
            System.out.println("任务完成");
        }
    }

    /**
     * 查询历史流程实例
     */
    @Test
    public void queryHistoryProcessInstance() {
        String processInstanceId = "5001";   //流程实例ID

        List<HistoricProcessInstance> historicProcessInstanceList = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
        System.out.println("查询历史流程实例 : " + historicProcessInstanceList);
    }


    /**
     * 查询历史任务
     */
    @Test
    public void queryHistoryTask() {
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery().list();
        System.out.println("查询历史任务 = " + historicTaskInstanceList.toString());
    }


    /**
     * 查询历史流程实例
     */
    @Test
    public void queryHistoryActivitiInstance() {
        List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery().list();
        System.out.println("查询历史流程实例 = " + historicActivityInstanceList.toString());
    }
}