package com.wenlincheng.pika.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenlincheng.pika.upms.entity.form.dict.DictTypeForm;
import com.wenlincheng.pika.upms.entity.po.DictType;
import com.wenlincheng.pika.upms.entity.query.dict.DictTypePageQuery;
import com.wenlincheng.pika.upms.entity.vo.dict.DictTypePageVO;
import com.wenlincheng.pika.upms.entity.vo.dict.DictTypeVO;

import java.util.List;

/**
 * <p>
 * 数据字典类型 服务类
 * </p>
 *
 * @author Pikaman
 * @since 2021-04-18
 */
public interface DictTypeService extends IService<DictType> {

    /**
     * 分页查询
     *
     * @param pageQuery 分页查询参数
     * @return IPage<DictTypeListVO>
     */
    IPage<DictTypePageVO> queryPageList(DictTypePageQuery pageQuery);

    /**
     * 新增字典类型
     * 
     * @param dictTypeForm 字典类型表单
     * @return java.lang.Boolean
     */
    Boolean addDictType(DictTypeForm dictTypeForm);

    /**
     * 修改字典类型
     *
     * @param dictTypeForm 字典类型表单
     * @return java.lang.Boolean
     */
    Boolean updateDictType(DictTypeForm dictTypeForm);

    /**
     * 查询字典类型
     *
     * @param id 字典类型id
     * @return DictTypeVO
     */
    DictTypeVO getDictType(Long id);

    /**
     * 删除字典类型
     *
     * @param id 字典类型id
     * @return java.lang.Boolean
     */
    Boolean deleteDictType(Long id);

    /**
     * 根据编码查询字典类型及值列表
     *
     * @param code 编码
     * @return DictTypeVO
     */
    DictTypeVO getDictTypeByCode(String code);

    /**
     * 查询列表
     *
     * @return List<DictTypeVO>
     */
    List<DictTypeVO> queryList();
}
