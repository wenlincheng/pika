package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenlincheng.pika.upms.entity.po.DictValue;
import com.wenlincheng.pika.upms.entity.vo.dict.DictValueVO;
import com.wenlincheng.pika.upms.mapper.DictValueMapper;
import com.wenlincheng.pika.upms.service.DictValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典值 服务实现类
 * </p>
 *
 * @author Pikaman
 * @since 2021-04-18
 */
@Service
public class DictValueServiceImpl extends ServiceImpl<DictValueMapper, DictValue> implements DictValueService {

    @Override
    public List<DictValueVO> getByDictTypeId(Long dictTypeId) {
        QueryWrapper<DictValue> valueQueryWrapper = new QueryWrapper<>();
        valueQueryWrapper.lambda().eq(DictValue::getDictTypeId, dictTypeId);
        List<DictValue> dictValueList = this.list(valueQueryWrapper);
        List<DictValueVO> dictValueVOList = new ArrayList<>();
        for (DictValue dictValue : dictValueList) {
            DictValueVO dictValueVO = new DictValueVO(dictValue);
            dictValueVOList.add(dictValueVO);
        }
        return dictValueVOList;
    }
}
