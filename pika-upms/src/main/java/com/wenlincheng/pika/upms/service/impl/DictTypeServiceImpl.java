package com.wenlincheng.pika.upms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlincheng.pika.common.core.base.query.PageParam;
import com.wenlincheng.pika.common.core.exception.PikaException;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import com.wenlincheng.pika.upms.entity.form.dict.DictTypeForm;
import com.wenlincheng.pika.upms.entity.po.DictType;
import com.wenlincheng.pika.upms.entity.po.DictValue;
import com.wenlincheng.pika.upms.entity.query.dict.DictTypePageQuery;
import com.wenlincheng.pika.upms.entity.vo.dict.DictTypeListVO;
import com.wenlincheng.pika.upms.entity.vo.dict.DictTypeVO;
import com.wenlincheng.pika.upms.entity.vo.dict.DictValueVO;
import com.wenlincheng.pika.upms.enums.UpmsErrorCodeEnum;
import com.wenlincheng.pika.upms.mapper.DictTypeMapper;
import com.wenlincheng.pika.upms.service.DictTypeService;
import com.wenlincheng.pika.upms.service.DictValueService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.DELETE_FAIL;
import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.QUERY_FAIL;
import static com.wenlincheng.pika.upms.constant.UpmsConstants.DICT_TYPE_CACHE_KEY_PREFIX;

/**
 * <p>
 * 数据字典类型 服务实现类
 * </p>
 *
 * @author Pikaman
 * @since 2021-04-18
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {

    @Autowired
    private DictValueService dictValueService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public IPage<DictTypeListVO> queryPageList(DictTypePageQuery pageQuery) {
        QueryWrapper<DictType> queryWrapper = pageQuery.buildWrapper();
        queryWrapper.lambda().eq(StringUtils.isNotBlank(pageQuery.getName()), DictType::getName, pageQuery.getName());
        PageParam<DictType> page = this.page(pageQuery.getPage(), queryWrapper);
        IPage<DictTypeListVO> pageList = page.convert(DictTypeListVO::new);
        if (CollectionUtils.isNotEmpty(pageList.getRecords())) {
            pageList.getRecords().forEach(dictTypeListVO -> {
                QueryWrapper<DictValue> valueQueryWrapper = new QueryWrapper<>();
                valueQueryWrapper.lambda().eq(DictValue::getDictTypeId, dictTypeListVO.getId());
                int count = dictValueService.count(valueQueryWrapper);
                dictTypeListVO.setValueNum(count);
            });
        }
        return pageList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addDictType(DictTypeForm dictTypeForm) {
        QueryWrapper<DictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DictType::getCode, dictTypeForm.getCode());
        if (this.count(queryWrapper) > 0) {
            throw PikaException.construct(UpmsErrorCodeEnum.CREATE_FAIL).appendMsg("字典编码已存在").build();
        }

        DictType dictType = dictTypeForm.toPo(DictType.class);
        this.save(dictType);
        // 存储字典值
        if (CollectionUtils.isNotEmpty(dictTypeForm.getDictValues())) {
            dictTypeForm.getDictValues().forEach(dictValueForm -> {
                DictValue dictValue = dictValueForm.toPo(DictValue.class);
                dictValue.setDictTypeId(dictType.getId());
                dictValueService.save(dictValue);
            });
        }
        return true;
    }

    @Override
    public Boolean updateDictType(DictTypeForm dictTypeForm) {
        DictType dictType = this.getById(dictTypeForm.getId());
        if (Objects.isNull(dictType)) {
            throw PikaException.construct(QUERY_FAIL).appendMsg("字典类型不存在").build();
        }
        DictType dictTypeUpdate = dictTypeForm.toPo(DictType.class);
        this.updateById(dictTypeUpdate);
        QueryWrapper<DictValue> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.lambda().eq(DictValue::getDictTypeId, dictTypeForm.getId());
        dictValueService.remove(valueQueryWrapper);
        // 存储字典值
        if (CollectionUtils.isNotEmpty(dictTypeForm.getDictValues())) {
            dictTypeForm.getDictValues().forEach(dictValueForm -> {
                DictValue dictValue = dictValueForm.toPo(DictValue.class);
                dictValue.setId(null);
                dictValue.setDictTypeId(dictType.getId());
                dictValueService.save(dictValue);
            });
        }
        redisUtils.delete(DICT_TYPE_CACHE_KEY_PREFIX + dictType.getCode());
        getDictTypeByCode(dictType.getCode());
        return true;
    }

    @Override
    public DictTypeVO getDictType(Long id) {
        DictType dictType = this.getById(id);
        if (Objects.isNull(dictType)) {
            throw PikaException.construct(QUERY_FAIL).appendMsg("字典类型不存在").build();
        }
        DictTypeVO dictTypeVO = new DictTypeVO(dictType);
        QueryWrapper<DictValue> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.lambda().eq(DictValue::getDictTypeId, id);
        List<DictValue> dictValueList = dictValueService.list(valueQueryWrapper);
        ArrayList<DictValueVO> dictValueVOS = new ArrayList<>();
        for (DictValue dictValue : dictValueList) {
            DictValueVO valueVO = new DictValueVO(dictValue);
            dictValueVOS.add(valueVO);
        }
        dictTypeVO.setDictValues(dictValueVOS);
        return dictTypeVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteDictType(Long id) {
        DictType dictType = this.getById(id);
        if (Objects.isNull(dictType)) {
            throw PikaException.construct(DELETE_FAIL).appendMsg("字典类型不存在").build();
        }
        this.removeById(id);
        QueryWrapper<DictValue> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.lambda().eq(DictValue::getDictTypeId, id);
        dictValueService.remove(valueQueryWrapper);

        redisUtils.delete(DICT_TYPE_CACHE_KEY_PREFIX + dictType.getCode());
        return true;
    }

    @Override
    public DictTypeVO getDictTypeByCode(String code) {
        String cacheDict = redisUtils.get(DICT_TYPE_CACHE_KEY_PREFIX + code);
        if (StringUtils.isNotBlank(cacheDict)) {
            return JSON.parseObject(cacheDict, DictTypeVO.class);
        }
        QueryWrapper<DictType> typeQueryWrapper = new QueryWrapper<>();
        typeQueryWrapper.lambda().eq(DictType::getCode, code);
        DictType dictType = this.getOne(typeQueryWrapper);
        if (Objects.isNull(dictType)) {
            throw PikaException.construct(QUERY_FAIL).appendMsg("字典类型不存在").build();
        }
        DictTypeVO dictTypeVO = new DictTypeVO(dictType);
        // 字典值
        List<DictValueVO> dictValueVOList = dictValueService.getByDictTypeId(dictType.getId());
        dictTypeVO.setDictValues(dictValueVOList);

        redisUtils.set(DICT_TYPE_CACHE_KEY_PREFIX + code, JSON.toJSONString(dictTypeVO));

        return dictTypeVO;
    }

    /**
     * 初始化字典类型到换粗
     *
     * @return void
     */
    @PostConstruct
    public void initDictType() {
        List<DictType> dictTypeList = this.list();
        for (DictType dictType : dictTypeList) {
            // 字典值
            DictTypeVO dictTypeVO = new DictTypeVO(dictType);
            List<DictValueVO> dictValueVOList = dictValueService.getByDictTypeId(dictType.getId());
            dictTypeVO.setDictValues(dictValueVOList);
            redisUtils.set(DICT_TYPE_CACHE_KEY_PREFIX + dictType.getCode(), JSON.toJSONString(dictTypeVO));
        }

    }
}
