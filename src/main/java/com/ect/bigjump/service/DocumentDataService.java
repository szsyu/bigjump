package com.ect.bigjump.service;

import com.ect.bigjump.domain.DocumentData;
import com.ect.bigjump.domain.DocumentDetail;
import com.ect.bigjump.domain.DocumentMaster;

/**
 * DocumentData服务层接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
public interface DocumentDataService extends BaseService<DocumentData, Long> {

    /**
     * 展现Document Detail
     *
     * @param documentMaster
     * @return
     */
    DocumentDetail getDocumentDetail(DocumentMaster documentMaster) throws Exception;
}
