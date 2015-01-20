package com.ect.bigjump.activiti.service.Impl;

import com.ect.bigjump.activiti.service.ProcessInstanceService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 流程实例Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    @Resource(name = "runtimeService")
    private RuntimeService runtimeService;

    @Override
    public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> map) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, map);
        return processInstance;
    }
}
