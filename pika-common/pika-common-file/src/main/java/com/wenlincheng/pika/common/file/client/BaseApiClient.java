package com.wenlincheng.pika.common.file.client;

import cn.hutool.core.date.DateUtil;
import com.wenlincheng.pika.common.file.entity.VirtualFile;
import com.wenlincheng.pika.common.file.exception.GlobalFileException;
import com.wenlincheng.pika.common.file.exception.OssApiException;
import com.wenlincheng.pika.common.file.exception.QiniuApiException;
import com.wenlincheng.pika.common.file.util.FileUtil;
import com.wenlincheng.pika.common.file.util.ImageUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

/**
 * 文件存储抽象类
 *
 * @author  wenlincheng
 * @date  2019-09-06 13:21
 * @version 1.0
 */
public abstract class BaseApiClient implements ApiClient {

    protected String storageType;
    protected String newFileName;
    protected String suffix;

    public BaseApiClient(String storageType) {
        this.storageType = storageType;
    }

    @Override
    public VirtualFile uploadImg(MultipartFile file) {
        this.check();
        if (file == null) {
            throw new OssApiException("[" + this.storageType + "]文件上传失败：文件不可为空");
        }
        try {
            VirtualFile res = this.uploadImg(file.getInputStream(), file.getOriginalFilename());
            VirtualFile imageInfo = ImageUtil.getInfo(file);
            res.setSize(imageInfo.getSize());
            res.setOriginalFileName(imageInfo.getOriginalFileName());
            if (FileUtil.isPicture(imageInfo.getSuffix())) {
                res.setWidth(imageInfo.getWidth());
                res.setHeight(imageInfo.getHeight());
            }
            return res;
        } catch (IOException e) {
            throw new GlobalFileException("[" + this.storageType + "]文件上传失败：" + e.getMessage());
        }
    }

    @Override
    public VirtualFile uploadImg(File file) {
        this.check();
        if (file == null) {
            throw new QiniuApiException("[" + this.storageType + "]文件上传失败：文件不可为空");
        }
        try {
            this.suffix = FileUtil.getSuffix(file);
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            VirtualFile res = this.uploadImg(is, "temp" + FileUtil.getSuffix(file));
            VirtualFile imageInfo = ImageUtil.getInfo(file);
            res.setSize(imageInfo.getSize());
            res.setOriginalFileName(file.getName());
            if (FileUtil.isPicture(imageInfo.getSuffix())) {
                res.setWidth(imageInfo.getWidth());
                res.setHeight(imageInfo.getHeight());
            }
            return res;
        } catch (FileNotFoundException e) {
            throw new GlobalFileException("[" + this.storageType + "]文件上传失败：" + e.getMessage());
        }
    }

    void createNewFileName(String key, String pathPrefix) {
        this.suffix = FileUtil.getSuffix(key);
        String fileName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        this.newFileName = pathPrefix + (fileName + this.suffix);
    }

    protected abstract void check();
}
