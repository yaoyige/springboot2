package com.yyg.sbt.service;

import com.yyg.sbt.store.domain.Attachment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yaoyige
 * @since 2019-06-02
 */
public interface AttachmentService extends IService<Attachment> {
    /**
     * 上传单个附件
     * @param bizId TD
     * @param bizType 类型
     * @param file 文件
     * @return 附件对象
     * @throws IOException
     */
    Attachment uploadAttch(Long bizId, String bizType, MultipartFile file) throws IOException;

    /**
     * 批量上传文件
     * @param bizId
     * @param bizType
     * @param file
     * @return
     * @throws IOException
     */
    Map<String,Object> uploadAttchs(Long bizId, String bizType, MultipartFile[] file) throws IOException;
    /**
     * 批量上传文件 事务管理
     * @param bizId
     * @param bizType
     * @param file
     * @return
     * @throws IOException
     */
    void uploadAttchsWithTX(Long bizId, String bizType, MultipartFile[] file) throws IOException;

    /**
     * 功能描述:批量下载相关附件
     * @param bizId
     * @param bizType
     * @param ids
     * @param response
     */
    void batchDownRelate(Long bizId, String bizType, Long[] ids, HttpServletResponse response);
}
