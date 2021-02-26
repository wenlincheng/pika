package com.wenlincheng.pika.common.data.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wenlincheng.pika.common.core.session.PikaUser;
import com.wenlincheng.pika.common.core.session.UserSessionHolder;
import com.wenlincheng.pika.common.core.annotation.PikaModel;
import com.wenlincheng.pika.common.leaf.enums.SequenceTypeEnum;
import com.wenlincheng.pika.common.leaf.model.SequenceConfig;
import com.wenlincheng.pika.common.leaf.service.LeafSegmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 字段自动填充
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Component
public class PikaMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private LeafSegmentService segmentService;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("创建自动填充");
        // 根据规则生成编码
        Class<?> aClass = metaObject.getOriginalObject().getClass();
        PikaModel.Code codeAnnotation = aClass.getAnnotation(PikaModel.Code.class);
        if (Objects.nonNull(codeAnnotation)) {
            SequenceConfig sequenceConfig = new SequenceConfig();
            sequenceConfig.setKey(aClass.getName())
                    .setType(codeAnnotation.type())
                    .setSize(codeAnnotation.size())
                    .setPrefix(codeAnnotation.prefix())
                    .setSuffix(codeAnnotation.suffix())
                    .setStep(codeAnnotation.step())
                    .setSeparator(codeAnnotation.separator())
                    .setFormat(codeAnnotation.format());

            String code;
            if (SequenceTypeEnum.DATE_ORDERLY_SEQ.getValue().equals(sequenceConfig.getType())) {
                code = segmentService.genDateOrderlySeq(sequenceConfig);
            } else {
                code = segmentService.genCode(sequenceConfig);
            }
            this.setFieldValByName("code", code, metaObject);
        }

        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "isDeleted", Integer.class, 0);
        this.strictInsertFill(metaObject, "createUserId", Long.class, getUserId());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("修改自动填充");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updateUserId", Long.class, getUserId());

    }

    /**
     * 获取用户id
     *
     * @return java.lang.Long
     */
    private Long getUserId() {
        PikaUser pikaUser = UserSessionHolder.getInstance().getUser();
        if (Objects.nonNull(pikaUser)) {
            return pikaUser.getId();
        }
        return null;
    }
}
