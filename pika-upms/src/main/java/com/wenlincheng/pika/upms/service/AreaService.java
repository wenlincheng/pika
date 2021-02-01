package com.wenlincheng.pika.upms.service;

import com.wenlincheng.pika.upms.entity.po.Area;
import com.wenlincheng.pika.upms.entity.vo.region.AreaDetailVO;
import com.wenlincheng.pika.upms.entity.vo.region.AreaListVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地址区域表 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface AreaService extends IService<Area> {
    /**
     * 根据级别查询地址区域
     *
     * @param level 级别
     * @return java.util.List<com.wenlincheng.pika.user.entity.vo.AreaListVO>
     * @date 2021/1/1 10:10 上午
     */
    List<AreaListVO> getAreaListByLevel(Integer level);

    /**
     * 根据父级id查询地址区域
     *
     * @param parentId 父级id
     * @return java.util.List<com.wenlincheng.pika.user.entity.vo.AreaListVO>
     * @date 2021/1/1 10:10 上午
     */
    List<AreaListVO> getAreaListByParentId(Long parentId);

    /**
     * 根据id查询区域详情
     *
     * @param id 区域id
     * @return com.wenlincheng.pika.user.entity.vo.AreaDetailVO
     * @date 2021/1/1 10:10 上午
     */
    AreaDetailVO getAreaDetailById(Long id);
}
