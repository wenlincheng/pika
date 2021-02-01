package com.wenlincheng.pika.common.leaf.service.impl;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.sankuai.inf.leaf.service.SegmentService;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.leaf.enums.LeafErrorCodeEnum;
import com.wenlincheng.pika.common.leaf.enums.SequenceEnum;
import com.wenlincheng.pika.common.leaf.model.SequenceConfig;
import com.wenlincheng.pika.common.leaf.service.LeafSegmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.wenlincheng.pika.common.leaf.enums.LeafErrorCodeEnum.*;

/**
 * 分片id
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
    public Long genId(String key) throws BaseException{
        Result result = segmentService.getId(key);
        long id = result.getId();
        if (id <= 0) {
            throw new BaseException(ID_GEN_ERROR);
        }
        return id;
    }

    @Override
    public String genCodeDateSeq(SequenceConfig seqConfig) {

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
            genIdExp(leafResult, key, SequenceEnum.DATE_SEQ, NOT_EXIST_LEAF_ALLOC_CONFIG_ERROR);
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
            log.error("Leaf Id 丢失 [{}] [{}]", SequenceEnum.DATE_SEQ, LeafErrorCodeEnum.ID_GEN_ERROR.getMsg());
            throw new BaseException(LeafErrorCodeEnum.ID_GEN_ERROR);
        }

        if (suffixLen > 0) {
            strBuilder.append(suffix);
        }

        return strBuilder.toString();
    }

    public void genIdExp(Result leafRt, String code, SequenceEnum sequenceEnum, LeafErrorCodeEnum expEnumBid) throws BaseException {
        long eid = leafRt.getId();
        if (eid == -1) {
            log.error("Leaf Code: [{}] [{}] [{}] [{}]", sequenceEnum, leafRt.getId(), code, LeafErrorCodeEnum.ID_CACHE_INIT_FALSE.getMsg());
            throw new BaseException(LeafErrorCodeEnum.ID_CACHE_INIT_FALSE);
        } else if (eid == -2) {
            log.error("Leaf Code: [{}] [{}] [{}] [{}]", sequenceEnum, leafRt.getId(), code, LeafErrorCodeEnum.ID_KEY_NOT_EXISTS.getMsg());
            throw new BaseException(LeafErrorCodeEnum.ID_KEY_NOT_EXISTS);
        } else if (eid == -3) {
            log.error("Leaf Code: [{}] [{}] [{}] [{}]", sequenceEnum, leafRt.getId(), code, LeafErrorCodeEnum.ID_TWO_SEGMENTS_ARE_NULL.getMsg());
            throw new BaseException(LeafErrorCodeEnum.ID_TWO_SEGMENTS_ARE_NULL);
        }
        log.error("Leaf Code: [{}] [{}] [{}] [{}]", sequenceEnum, leafRt.getId(), code, expEnumBid.getMsg());
        throw new BaseException(ID_GEN_ERROR);
    }
}
