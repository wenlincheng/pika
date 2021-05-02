package com.wenlincheng.pika.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.upms.entity.query.log.LogPageQuery;
import com.wenlincheng.pika.upms.entity.po.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenlincheng.pika.upms.entity.vo.log.LogDetailVO;
import com.wenlincheng.pika.upms.entity.vo.log.LogPageVO;

/**
 * <p>
 * 系统操作日志表 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 分页查询
     *
     * @param pageQuery 分页查询参数
     * @return IPage<LogListVO>
     */
    IPage<LogPageVO> queryPageList(LogPageQuery pageQuery);

    /**
     * 查询日志详情
     *
     * @param id 日志id
     * @return LogDetailVO
     */
    LogDetailVO getDetailById(Long id);
}
