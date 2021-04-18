package com.wenlincheng.pika.common.data.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class CodeGenerator {

    @Autowired
    private GeneratorProperties generatorProperties;

    public void gen() {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir") + "/" + generatorProperties.getServerName();
        // 生成文件输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 开发人员
        gc.setAuthor(generatorProperties.getAuthor());
        // 是否打开输出目录
        gc.setOpen(true);
        gc.setControllerName("%sController");
        // service命名方式
        gc.setServiceName("%sService");
        // service impl命名方式
        gc.setServiceImplName("%sServiceImpl");
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(true);
        // 日期类型
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(generatorProperties.getDatabase().getUrl());
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(generatorProperties.getDatabase().getUsername());
        dsc.setPassword(generatorProperties.getDatabase().getPassword());
        // 自定义类型转换
        dsc.setTypeConvert(new CustomMySqlTypeConvert());
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        // 父包模块名
        // pc.setModuleName("order");
        // 父包名。
        // 自定义包路径  如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent(generatorProperties.getPackagePath() + "." + generatorProperties.getPackageName());
        pc.setEntity("entity.po");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {

            @Override
            public String outputFile(TableInfo arg0) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/"// + pc.getModuleName()
                        + arg0.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 自定义继承的Entity类全称，带包名
        // strategy.setSuperEntityClass("com.wenlincheng.pika.common.entity.po.BasePo");
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        // 添加@TableName("order")注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        // 自定义继承的Controller类全称，带包名
        // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 需要包含的表名，允许正则表达式
        String[] tableNames = generatorProperties.getTableNames();
        strategy.setInclude(tableNames);
        // 自定义基础的Entity类，公共字段
        strategy.setSuperEntityColumns("id");
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}

