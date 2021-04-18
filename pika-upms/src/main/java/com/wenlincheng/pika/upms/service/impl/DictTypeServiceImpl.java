package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlincheng.pika.common.core.base.query.PageParam;
import com.wenlincheng.pika.upms.entity.form.dict.DictTypeForm;
import com.wenlincheng.pika.upms.entity.po.DictType;
import com.wenlincheng.pika.upms.entity.po.DictValue;
import com.wenlincheng.pika.upms.entity.query.dict.DictTypePageQuery;
import com.wenlincheng.pika.upms.entity.vo.dict.DictTypeListVO;
import com.wenlincheng.pika.upms.entity.vo.dict.DictTypeVO;
import com.wenlincheng.pika.upms.entity.vo.dict.DictValueVO;
import com.wenlincheng.pika.upms.mapper.DictTypeMapper;
import com.wenlincheng.pika.upms.service.DictTypeService;
import com.wenlincheng.pika.upms.service.DictValueService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public IPage<DictTypeListVO> queryPageList(DictTypePageQuery pageQuery) {
        QueryWrapper<DictType> queryWrapper = pageQuery.buildWrapper();
        queryWrapper.lambda().eq(DictType::getName, pageQuery.getName());
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
        DictType dictType = dictTypeForm.toPo(DictType.class);
        this.save(dictType);
        // 存储字典值
        if (CollectionUtils.isNotEmpty(dictTypeForm.getDictValueList())) {
            dictTypeForm.getDictValueList().forEach(dictValueForm -> {
                DictValue dictValue = dictValueForm.toPo(DictValue.class);
                dictValueService.save(dictValue);
            });
        }
        return true;
    }

    @Override
    public Boolean updateDictType(DictTypeForm dictTypeForm) {
        DictType dictType = dictTypeForm.toPo(DictType.class);
        this.updateById(dictType);
        QueryWrapper<DictValue> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.lambda().eq(DictValue::getDictTypeId, dictTypeForm.getId());
        dictValueService.remove(valueQueryWrapper);
        // 存储字典值
        if (CollectionUtils.isNotEmpty(dictTypeForm.getDictValueList())) {
            dictTypeForm.getDictValueList().forEach(dictValueForm -> {
                DictValue dictValue = dictValueForm.toPo(DictValue.class);
                dictValueService.save(dictValue);
            });
        }
        return true;
    }

    @Override
    public DictTypeVO getDictType(Long id) {
        DictType dictType = this.getById(id);
        DictTypeVO dictTypeVO = new DictTypeVO(dictType);
        QueryWrapper<DictValue> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.lambda().eq(DictValue::getDictTypeId, id);
        List<DictValue> dictValueList = dictValueService.list(valueQueryWrapper);
        ArrayList<DictValueVO> dictValueVOS = new ArrayList<>();
        for (DictValue dictValue : dictValueList) {
            DictValueVO valueVO = new DictValueVO(dictValue);
            dictValueVOS.add(valueVO);
        }
        dictTypeVO.setDictValueList(dictValueVOS);
        return dictTypeVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteDictType(Long id) {
        this.removeById(id);
        QueryWrapper<DictValue> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.lambda().eq(DictValue::getDictTypeId, id);
        return dictValueService.remove(valueQueryWrapper);
    }
}
