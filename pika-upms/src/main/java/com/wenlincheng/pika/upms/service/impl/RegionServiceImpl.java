package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenlincheng.pika.upms.entity.po.Region;
import com.wenlincheng.pika.upms.entity.vo.region.RegionListVO;
import com.wenlincheng.pika.upms.entity.vo.region.RegionDetailVO;
import com.wenlincheng.pika.upms.mapper.RegionMapper;
import com.wenlincheng.pika.upms.service.RegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 地址区域表 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    @Override
    public List<RegionListVO> getAreaListByLevel(Integer level) {
        List<RegionListVO> list = new ArrayList<>();
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Region::getLevel, level);
        List<Region> areaList = this.list(queryWrapper);
        return convertArea(list, areaList);
    }

    @Override
    public List<RegionListVO> getAreaListByParentId(Long parentId) {
        List<RegionListVO> list = new ArrayList<>();
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Region::getParentId, parentId);
        List<Region> areaList = this.list(queryWrapper);
        return convertArea(list, areaList);
    }

    @Override
    public RegionDetailVO getAreaDetailById(Long id) {
        RegionDetailVO areaDetailVO = new RegionDetailVO();
        Region area = this.getById(id);
        areaDetailVO.setId(area.getId());
        String s = get(area.getParentId());
        areaDetailVO.setFullName(s + area.getName());
        return areaDetailVO;
    }

    private String get(Long parentId) {
        Region parentArea = this.getById(parentId);
        String name = "";
        if (parentArea != null) {
            name = parentArea.getName();
            String s = get(parentArea.getParentId());
            name = s + name + " ";
        }
        return name;
    }

    private List<RegionListVO> convertArea(List<RegionListVO> list, List<Region> areaList) {
        for (Region area :areaList) {
            RegionListVO areaListVO = new RegionListVO();
            areaListVO.setId(area.getId());
            areaListVO.setName(area.getName());
            areaListVO.setLevel(area.getLevel());
            list.add(areaListVO);
        }
        return list;
    }

}
