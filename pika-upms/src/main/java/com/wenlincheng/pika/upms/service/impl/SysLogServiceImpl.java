package com.wenlincheng.pika.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.common.core.base.query.PageParam;
import com.wenlincheng.pika.upms.entity.query.log.LogPageQuery;
import com.wenlincheng.pika.upms.entity.po.SysLog;
import com.wenlincheng.pika.upms.entity.vo.log.LogDetailVO;
import com.wenlincheng.pika.upms.entity.vo.log.LogPageVO;
import com.wenlincheng.pika.upms.mapper.SysLogMapper;
import com.wenlincheng.pika.upms.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 系统操作日志表 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public IPage<LogPageVO> queryPageList(LogPageQuery pageQuery) {
        QueryWrapper<SysLog> queryWrapper = pageQuery.buildWrapper();
        queryWrapper.lambda()
                .like(StringUtils.isNotBlank(pageQuery.getTitle()), SysLog::getTitle, pageQuery.getTitle())
                .like(StringUtils.isNotBlank(pageQuery.getServiceId()), SysLog::getServiceId, pageQuery.getServiceId())
                .like(StringUtils.isNotBlank(pageQuery.getUsername()), SysLog::getUsername, pageQuery.getUsername())
                .like(StringUtils.isNotBlank(pageQuery.getMethod()), SysLog::getMethod, pageQuery.getMethod())
                .gt(Objects.nonNull(pageQuery.getTime()), SysLog::getTime, pageQuery.getTime())
                .eq(Objects.nonNull(pageQuery.getType()), SysLog::getType, pageQuery.getType())
                .orderByDesc(SysLog::getCreateTime);
        PageParam<SysLog> logPage = this.page(pageQuery.getPage(), queryWrapper);
        return logPage.convert(LogPageVO::new);
    }

    @Override
    public LogDetailVO getDetailById(Long id) {
        SysLog log = this.getById(id);
        return new LogDetailVO(log);
    }
}
