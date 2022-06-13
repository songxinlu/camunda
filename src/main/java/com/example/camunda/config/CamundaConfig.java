//package com.example.camunda.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.camunda.bpm.engine.HistoryService;
//import org.camunda.bpm.engine.IdentityService;
//import org.camunda.bpm.engine.ManagementService;
//import org.camunda.bpm.engine.ProcessEngineConfiguration;
//import org.camunda.bpm.engine.RepositoryService;
//import org.camunda.bpm.engine.RuntimeService;
//import org.camunda.bpm.engine.TaskService;
//import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
//import org.camunda.bpm.engine.impl.history.HistoryLevel;
//import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
//import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * @author zhoupeng
// */
//@Configuration
//@Slf4j
//public class CamundaConfig {
//
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    @Bean(name = "myProcessEngineConfiguration")
//    public ProcessEngineConfiguration processEngineConfiguration() {
//        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
//        processEngineConfiguration.setDatabaseSchemaUpdate("false");
//        processEngineConfiguration.setDataSource(dataSource);
//        processEngineConfiguration.setTransactionManager(transactionManager);
//        processEngineConfiguration.setDatabaseType("mysql");
//        processEngineConfiguration.setHistoryLevel(HistoryLevel.HISTORY_LEVEL_FULL);
//        return processEngineConfiguration;
//    }
//
//
//    @Bean(name = "myProcessEngineFactoryBean")
//    public ProcessEngineFactoryBean processEngine(ProcessEngineConfiguration processEngineConfiguration) {
//        ProcessEngineFactoryBean processEngine = new ProcessEngineFactoryBean();
//        processEngine.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
//        log.info("initial camunda engine...");
//        return processEngine;
//    }
//
//
//    @Bean(name = "myRepositoryService")
//    public RepositoryService repositoryService(ProcessEngineFactoryBean processEngine) throws Exception {
//        return processEngine.getObject().getRepositoryService();
//    }
//
//    @Bean(name = "myRuntimeService")
//    public RuntimeService runtimeService(ProcessEngineFactoryBean processEngine) throws Exception {
//        return processEngine.getObject().getRuntimeService();
//    }
//
//    @Bean(name = "myTaskService")
//    public TaskService taskService(ProcessEngineFactoryBean processEngine) throws Exception {
//        return processEngine.getObject().getTaskService();
//    }
//
//    @Bean(name = "myHistoryService")
//    public HistoryService historyService(ProcessEngineFactoryBean processEngine) throws Exception {
//        return processEngine.getObject().getHistoryService();
//    }
//
//    @Bean(name = "myManagementService")
//    public ManagementService managementService(ProcessEngineFactoryBean processEngine) throws Exception {
//        return processEngine.getObject().getManagementService();
//    }
//
//    @Bean(name = "myIdentityService")
//    public IdentityService identityService(ProcessEngineFactoryBean processEngine) throws Exception {
//        return processEngine.getObject().getIdentityService();
//    }
//}
