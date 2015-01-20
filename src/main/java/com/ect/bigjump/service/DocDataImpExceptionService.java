package com.ect.bigjump.service;

import com.ect.bigjump.domain.DocDataImpException;

/**
 * Document Data导入异常记录Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
public interface DocDataImpExceptionService extends BaseService<DocDataImpException, Long> {

    /**
     * 新增数据导入异常对象
     *
     * @param docDataIfaceId
     * @param errorMessage
     */
    void newDataImpException(Long docDataIfaceId, String errorMessage);
}
