package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenlincheng.pika.upms.entity.po.Area;
import com.wenlincheng.pika.upms.entity.vo.region.AreaListVO;
import com.wenlincheng.pika.upms.entity.vo.region.AreaDetailVO;
import com.wenlincheng.pika.upms.mapper.AreaMapper;
import com.wenlincheng.pika.upms.service.AreaService;
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
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<AreaListVO> getAreaListByLevel(Integer level) {
        List<AreaListVO> list = new ArrayList<>();
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", level);
        List<Area> areaList = this.baseMapper.selectList(queryWrapper);
        return convertArea(list, areaList);
    }

    @Override
    public List<AreaListVO> getAreaListByParentId(Long parentId) {
        List<AreaListVO> list = new ArrayList<>();
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<Area> areaList = areaMapper.selectList(queryWrapper);
        return convertArea(list, areaList);
    }

    @Override
    public AreaDetailVO getAreaDetailById(Long id) {
        AreaDetailVO areaDetailVO = new AreaDetailVO();
        Area area = areaMapper.selectById(id);
        areaDetailVO.setId(area.getId());
        String s = get(area.getParentId());
        areaDetailVO.setFullName(s + area.getName());
        return areaDetailVO;
    }

    private String get(Long parentId) {
        Area parentArea = areaMapper.selectById(parentId);
        String name = "";
        if (parentArea != null) {
            name = parentArea.getName();
            String s = get(parentArea.getParentId());
            name = s + name + " ";
        }
        return name;
    }

    private List<AreaListVO> convertArea(List<AreaListVO> list, List<Area> areaList) {
        for (Area area :areaList) {
            AreaListVO areaListVO = new AreaListVO();
            areaListVO.setId(area.getId());
            areaListVO.setName(area.getName());
            areaListVO.setLevel(area.getLevel());
            list.add(areaListVO);
        }
        return list;
    }

}
