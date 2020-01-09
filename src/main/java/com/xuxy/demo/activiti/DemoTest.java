package com.xuxy.demo.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;

public class DemoTest {

    public static void main(String[] args) {

        //获取流程引擎
        ProcessEngineConfiguration processEngineConfiguration =
                ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine =        processEngineConfiguration.buildProcessEngine();

        System.out.println(processEngine.getName());
        System.out.println(ProcessEngine.VERSION);

        //启动流程
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("maintenance.bpmn");
        Deployment deployment = deploymentBuilder.deploy();
        deployment.getId();
        System.out.println("部署id"+deployment.getName());


        //处理任务
        //结束流程
    }
}
