package com.ect.bigjump.service;

import com.ect.bigjump.domain.DocumentMaster;
import com.ect.bigjump.domain.DocumentReply;

import java.util.List;

/**
 * Document Reply Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
public interface DocumentReplyService extends BaseService<DocumentReply, Long> {

    /**
     * 获取Document Matser当前最大的Sequence,如果不存在，则返回0
     *
     * @param docMasterId
     * @return
     */
    Integer getMaxSeqForDocMaster(Long docMasterId);

    /**
     * 获取相应Document Master的签核历史记录
     *
     * @return
     */
    List<DocumentReply> getDocumentReplyListByDocumentMaster(DocumentMaster documentMaster);
}
