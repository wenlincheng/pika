package com.wenlincheng.pika.upms.service;

import com.wenlincheng.pika.upms.entity.po.Region;
import com.wenlincheng.pika.upms.entity.vo.region.RegionDetailVO;
import com.wenlincheng.pika.upms.entity.vo.region.RegionListVO;
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
public interface RegionService extends IService<Region> {
    /**
     * 根据级别查询地址区域
     *
     * @param level 级别
     * @return List<RegionListVO>
     */
    List<RegionListVO> getAreaListByLevel(Integer level);

    /**
     * 根据父级id查询地址区域
     *
     * @param parentId 父级id
     * @return List<RegionListVO>
     */
    List<RegionListVO> getAreaListByParentId(Long parentId);

    /**
     * 根据id查询区域详情
     *
     * @param id 区域id
     * @return RegionDetailVO
     */
    RegionDetailVO getAreaDetailById(Long id);
}
