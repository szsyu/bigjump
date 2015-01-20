package com.ect.bigjump.service;

import com.ect.bigjump.domain.DocDataInterface;

import java.util.List;

/**
 * Document Data导入接口Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
public interface DocDataInterfaceService extends BaseService<DocDataInterface, Long> {

    /**
     * 获取接口表里未处理的Document Data,当processStatus为N时
     *
     * @return
     */
    List<DocDataInterface> getUnprocessedData(Integer dataLevel);

    /**
     * 获取下一Level的数据
     *
     * @param parentData
     * @return
     */
    List<DocDataInterface> getChildData(DocDataInterface parentData);
}
