package com.ect.bigjump.activiti.service;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * 流程实例Serivce接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
public interface ProcessInstanceService {

    /**
     * 根据流程定义ID启动新的流程实例
     *
     * @param processDefinitionId
     * @param map
     * @return
     */
    ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> map);

}
