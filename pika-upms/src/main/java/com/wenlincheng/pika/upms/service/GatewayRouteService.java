package com.wenlincheng.pika.upms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.upms.entity.query.gateway.GatewayRoutePageQuery;
import com.wenlincheng.pika.upms.entity.vo.gateway.GatewayRouteVO;
import com.wenlincheng.pika.upms.entity.po.GatewayRoute;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网关路由表 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface GatewayRouteService extends IService<GatewayRoute> {

    /**
     * 获取
     *
     * @param id 路由id
     * @return com.wenlincheng.pika.gateway.entity.po.GatewayRoute
     */
    GatewayRoute get(Long id);

    /**
     * 新增
     *
     * @param gatewayRoute  路由
     * @return boolean
     */
    boolean add(GatewayRoute gatewayRoute);

    /**
     * 分页查询
     *
     * @param queryForm 分页请求参数
     * @return List<GatewayRouteVo>
     */
    IPage<GatewayRouteVO> pageList(GatewayRoutePageQuery queryForm);

    /**
     * 更新
     *
     * @param gatewayRoute 路由
     * @return boolean
     */
    boolean update(GatewayRoute gatewayRoute);

    /**
     * 根据id删除
     *
     * @param id 路由id
     * @return boolean
     */
    boolean delete(String id);

    /**
     * 重新加载网关路由配置到redis
     *
     * @return boolean
     */
    boolean refresh();

}
