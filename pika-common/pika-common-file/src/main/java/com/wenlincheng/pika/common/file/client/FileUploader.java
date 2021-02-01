package com.wenlincheng.pika.common.file.client;

import com.wenlincheng.pika.common.file.entity.VirtualFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * 文件上传
 *
 * @author  wenlincheng
 * @date  2019-09-06 13:21
 * @version 1.0
 */
public interface FileUploader {

    /**
     * 上传文件
     *
     * @param file       待上传的文件流
     * @param uploadType 文件上传类型，用来区分文件
     * @param suffix     文件后缀
     * @param save       是否保存
     */
    VirtualFile upload(InputStream file, String uploadType, String suffix, boolean save);

    /**
     * 上传文件
     *
     * @param file       待上传的文件
     * @param uploadType 文件上传类型，用来区分文件
     * @param save       是否保存
     */
    VirtualFile upload(File file, String uploadType, boolean save);

    /**
     * 上传文件
     *
     * @param file       待上传的文件
     * @param uploadType 文件上传类型，用来区分文件
     * @param save       是否保存
     */
    VirtualFile upload(MultipartFile file, String uploadType, boolean save);

    /**
     * 删除文件
     *
     * @param fileId   文件id
     */
    boolean delete(long fileId);
}
