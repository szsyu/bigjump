package com.ect.bigjump.test.service;

import com.ect.bigjump.domain.Document;
import com.ect.bigjump.domain.DocumentDataLevel;
import com.ect.bigjump.domain.LookupValue;
import com.ect.bigjump.service.DocumentDataLevelService;
import com.ect.bigjump.service.DocumentService;
import com.ect.bigjump.service.LookupValueService;
import com.ect.bigjump.utility.DateTimeParse;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath*:config/bigjump-activiti.xml","classpath*:config/bigjump-core.xml"})
public class TestActivitiService {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ProcessEngine processEngine;

	@Autowired
	private LookupValueService lookupValueService;

	@Autowired
	private TaskService taskService;




	@Test
	public void TestFileName(){

		String originalFilename = "sss.zip";
		String dateTimeStr = DateTimeParse.convertDate2Str(new Date(),"yyyyMMddHHmmss");
		String newFileName =  originalFilename.substring(0,originalFilename.lastIndexOf(".")) + dateTimeStr
				+ originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
		System.out.print(newFileName);
	}


	@Test
	public void TestProcessInstance(){
		Map<String,Object> map = new HashMap<>();
		map.put("lastApproval","2");
		//map.put("rejectedFlag","N");
		//List<LookupValue> lookupValueList = lookupValueService.getAll();

/*		ProcessInstance processInstance = runtimeService.
				                             startProcessInstanceById("PR_SUZ:41:197504", map);*/

		//Map<String,Object> variables = processInstance.getProcessVariables();
		//List<Task> taskList = processEngine.getTaskService().createTaskQuery().taskUnassigned().list();

		//String ss = processEngine.getRuntimeService().getVariable("60001", "COSTCENTER",String.class);

		//Task task = processEngine.getTaskService().createTaskQuery().taskId("60005").list().get(0);
		//String taskName = task.getTaskDefinitionKey();


         //分配任务

		//taskService.setAssignee("202507", "syu");
		//taskService.complete("245004", map);
		//taskService.complete("235005");
		List<IdentityLink> identityLinkList = taskService.getIdentityLinksForTask("272508");

		//processEngine.getProcessEngineConfiguration().getClass().get
	}

}
