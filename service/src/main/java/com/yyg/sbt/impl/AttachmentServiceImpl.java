package com.yyg.sbt.impl;

import com.yyg.sbt.service.AttachmentService;
import com.yyg.sbt.store.domain.Attachment;
import com.yyg.sbt.store.mapper.AttachmentMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yaoyige
 * @since 2019-06-02
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {
    /**
     * 上传单个附件
     * @param bizId TD
     * @param bizType 类型
     * @param file 文件
     * @return 附件对象
     * @throws IOException
     */
    @Override
    public Attachment uploadAttch(Long bizId, String bizType, MultipartFile file) throws IOException{

        return null;
    }

    /**
     * 批量上传文件
     * @param bizId
     * @param bizType
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public Map<String,Object> uploadAttchs(Long bizId, String bizType, MultipartFile[] file) throws IOException{
        return null;
    }
    /**
     * 批量上传文件 事务管理
     * @param bizId
     * @param bizType
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public void uploadAttchsWithTX(Long bizId, String bizType, MultipartFile[] file) throws IOException{

    }

    /**
     * 功能描述:批量下载相关附件
     * @param bizId
     * @param bizType
     * @param ids
     * @param response
     */
    @Override
    public void batchDownRelate(Long bizId, String bizType, Long[] ids, HttpServletResponse response){

    }
}
