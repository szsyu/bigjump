package com.ect.bigjump.service;

import com.ect.bigjump.domain.DocumentMaster;

import java.util.Set;

/**
 * Document Master Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
public interface DocumentMasterService extends BaseService<DocumentMaster, Long> {

    /**
     * 根据流程实例ID获取Document Master对象
     *
     * @param processIntanceId
     * @return
     */
    DocumentMaster getByProcessInstanceId(String processIntanceId);

}
