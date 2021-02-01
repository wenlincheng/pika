package com.wenlincheng.pika.common.file.client;


import com.wenlincheng.pika.common.file.enums.FileStorageTypeEnum;
import com.wenlincheng.pika.common.file.exception.OssApiException;
import com.wenlincheng.pika.common.file.alioss.api.OssApi;
import com.wenlincheng.pika.common.file.entity.VirtualFile;
import com.wenlincheng.pika.common.file.util.FileUtil;
import com.wenlincheng.pika.common.file.util.StreamUtil;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 阿里云OSS文件上传api
 *
 * @author  wenlincheng
 * @date  2019-09-06 13:21
 * @version 1.0
 */
public class AliYunOssApiClient extends BaseApiClient {
    private static final String DEFAULT_PREFIX = "pika/";
    private OssApi ossApi;
    private String url;
    private String bucketName;
    private String pathPrefix;

    public AliYunOssApiClient() {
        super("阿里云OSS");
    }

    public AliYunOssApiClient init(String endpoint, String accessKeyId, String accessKeySecret, String url, String bucketName, String uploadType) {
        this.ossApi = new OssApi(endpoint, accessKeyId, accessKeySecret);
        this.url = url;
        this.bucketName = bucketName;
        this.pathPrefix = StringUtils.isEmpty(uploadType) ? DEFAULT_PREFIX : uploadType.endsWith("/") ? uploadType : uploadType + "/";
        return this;
    }

    @Override
    public VirtualFile uploadImg(InputStream is, String imageUrl) {
        this.check();
        String key = FileUtil.generateTempFileName(imageUrl);
        this.createNewFileName(key, this.pathPrefix);
        Date startTime = new Date();
        try (InputStream uploadIs = StreamUtil.clone(is);
             InputStream fileHashIs = StreamUtil.clone(is)) {
            ossApi.uploadFile(uploadIs, this.newFileName, bucketName);
            return new VirtualFile()
                    .setOriginalFileName(FileUtil.getName(key))
                    .setSuffix(this.suffix)
                    .setUploadStartTime(startTime)
                    .setUploadEndTime(new Date())
                    .setFilePath(this.newFileName)
                    .setFileHash(DigestUtils.md5DigestAsHex(fileHashIs))
                    .setFullFilePath(this.url + this.newFileName)
                    .setStorageType(FileStorageTypeEnum.ALI_YUN.getValue());
        } catch (IOException e) {
            throw new OssApiException("[" + this.storageType + "]文件上传失败：" + e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean removeFile(String key) {
        this.check();
        if (StringUtils.isEmpty(key)) {
            throw new OssApiException("[" + this.storageType + "]删除文件失败：文件key为空");
        }
        try {
            this.ossApi.deleteFile(key, bucketName);
            return true;
        } catch (Exception e) {
            throw new OssApiException(e.getMessage());
        }
    }

    @Override
    public void check() {
        if (null == ossApi) {
            throw new OssApiException("[" + this.storageType + "]尚未配置阿里云OSS，文件上传功能暂时不可用！");
        }
    }
}
