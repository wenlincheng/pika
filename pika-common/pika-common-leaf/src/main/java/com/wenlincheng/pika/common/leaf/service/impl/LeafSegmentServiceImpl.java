package com.wenlincheng.pika.common.leaf.service.impl;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.sankuai.inf.leaf.service.SegmentService;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.common.leaf.enums.LeafErrorCodeEnum;
import com.wenlincheng.pika.common.leaf.enums.SequenceTypeEnum;
import com.wenlincheng.pika.common.leaf.model.SequenceConfig;
import com.wenlincheng.pika.common.leaf.service.LeafSegmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.wenlincheng.pika.common.leaf.enums.LeafErrorCodeEnum.*;

/**
 * 号段模式id生成器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Service
public class LeafSegmentServiceImpl implements LeafSegmentService {

    private static final Map<String, DateTimeFormatter> DATE_TIME_FORMATTER_MAP = new ConcurrentHashMap<>();

    @Autowired
    private SegmentService segmentService;

    @Override
    public Long genId(String key) throws PikaException {
        Result result = segmentService.getId(key);
        long id = result.getId();
        if (id <= 0) {
            throw PikaException.construct(ID_GEN_ERROR).build();
        }
        return id;
    }

    @Override
    public String genDateOrderlySeq(SequenceConfig seqConfig) {
        if (Objects.isNull(seqConfig)) {
            return null;
        }

        String key = seqConfig.getKey();
        String fmt = seqConfig.getFormat();
        String prefix = seqConfig.getPrefix();
        String separator = seqConfig.getSeparator();
        String suffix = seqConfig.getSuffix();
        Integer size = seqConfig.getSize();
        Result leafResult = segmentService.getId(seqConfig.getKey());

        Long leaf = null;
        if (Status.SUCCESS == leafResult.getStatus()) {
            leaf = leafResult.getId();
        } else if (Status.EXCEPTION == leafResult.getStatus()) {
            genIdExp(leafResult, key, SequenceTypeEnum.DATE_SEQ, NOT_EXIST_LEAF_ALLOC_CONFIG_ERROR);
        }

        String now = DATE_TIME_FORMATTER_MAP.computeIfAbsent(fmt, DateTimeFormatter::ofPattern).format(LocalDate.now());
        StringBuilder strBuilder = new StringBuilder();
        int len = 0;
        int suffixLen = 0;
        if (StringUtils.isNotBlank(prefix)) {
            strBuilder.append(prefix);
        }
        if (StringUtils.isNotBlank(separator)) {
            strBuilder.append(separator);
        }
        if (StringUtils.isNotBlank(fmt)) {
            strBuilder.append(now);
        }

        if (StringUtils.isNotBlank(suffix)) {
            suffixLen = suffix.length();
        }

        if (null != leaf && leaf > 0) {
            if (null != size && size > 0) {
                int rsize = size - len - suffixLen;
                if (rsize > 0 && rsize > String.valueOf(leaf).length()) {
                    strBuilder.append(String.format("%0" + rsize + "d", leaf));
                } else {
                    strBuilder.append(leaf);
                }
            } else {
                strBuilder.append(leaf);
            }
        } else {
            log.error("Leaf Id 丢失 [{}] [{}]", SequenceTypeEnum.DATE_SEQ, LeafErrorCodeEnum.ID_GEN_ERROR.getMsg());
            throw PikaException.construct(LeafErrorCodeEnum.ID_GEN_ERROR).build();
        }

        if (suffixLen > 0) {
            strBuilder.append(suffix);
        }

        return strBuilder.toString();
    }

    @Override
    public String genDateSeq(SequenceConfig seqConfig) {


        return null;
    }

    @Override
    public String genSeq(SequenceConfig seqConfig) {

        return null;
    }

    @Override
    public String genCode(SequenceConfig seqConfig) {
        if (Objects.isNull(seqConfig)) {
            return null;
        }
        String key = seqConfig.getKey();
        String fmt = seqConfig.getFormat();
        String prefix = seqConfig.getPrefix();
        String separator = seqConfig.getSeparator();
        String suffix = seqConfig.getSuffix();
        Integer size = seqConfig.getSize();

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        return null;
    }

    public void genIdExp(Result leafRt, String code, SequenceTypeEnum sequenceTypeEnum, LeafErrorCodeEnum expEnumBid) throws PikaException {
        long eid = leafRt.getId();
        if (eid == -1) {
            log.error("Leaf Code: [{}] [{}] [{}] [{}]", sequenceTypeEnum, leafRt.getId(), code, LeafErrorCodeEnum.ID_CACHE_INIT_FALSE.getMsg());
            throw PikaException.construct(LeafErrorCodeEnum.ID_CACHE_INIT_FALSE).build();
        } else if (eid == -2) {
            log.error("Leaf Code: [{}] [{}] [{}] [{}]", sequenceTypeEnum, leafRt.getId(), code, LeafErrorCodeEnum.ID_KEY_NOT_EXISTS.getMsg());
            throw PikaException.construct(LeafErrorCodeEnum.ID_KEY_NOT_EXISTS).build();
        } else if (eid == -3) {
            log.error("Leaf Code: [{}] [{}] [{}] [{}]", sequenceTypeEnum, leafRt.getId(), code, LeafErrorCodeEnum.ID_TWO_SEGMENTS_ARE_NULL.getMsg());
            throw PikaException.construct(LeafErrorCodeEnum.ID_TWO_SEGMENTS_ARE_NULL).build();
        }
        log.error("Leaf Code: [{}] [{}] [{}] [{}]", sequenceTypeEnum, leafRt.getId(), code, expEnumBid.getMsg());
        throw PikaException.construct(ID_GEN_ERROR).build();
    }
}
