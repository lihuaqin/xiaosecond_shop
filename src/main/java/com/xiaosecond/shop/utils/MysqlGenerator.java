package com.xiaosecond.shop.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.ArrayList;
import java.util.List;


/**
 * 代码生成器演示
 * @author
 *
 */
public class MysqlGenerator {

	public static void main(String[] args) {
	   String tableName = "t_cms_article";
        String[] tableNames = tableName.split(",");
        for (int i = 0; i <tableNames.length ; i++) {
            generatorTable(tableNames[i]);
        }
	}

	public static void generatorTable(String tableName){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("lhq");
        gc.setOpen(false);
        gc.setControllerName(handleTableName(tableName) + "Controller");
        gc.setServiceImplName(handleTableName(tableName) + "ServiceImpl");
        gc.setMapperName( handleTableName(tableName) + "Mapper");
        gc.setServiceName(handleTableName(tableName) + "Service");
        gc.setEntityName(handleTableName(tableName) + "Vo");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/xiaosecond_shop?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
//      dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();

        pc.setParent("com.xiaosecond.shop");
        pc.setEntity("vo");
        pc.setMapper("mapper");

        mpg.setPackageInfo(pc);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称================================模块名（自己设置）
                return projectPath + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName().replace("Vo" , "").replace("Table","") + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置**（个性化定制）**
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        //实体类自动继承Entity,不需要也可以
        strategy.setSuperEntityClass("com.xiaosecond.shop.vo.BaseVo");
        strategy.setEntityLombokModel(false);
//     strategy.setRestControllerStyle(true);
        //控制层自动继承父类BaseController,不需要也可以
//     strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        //设置要被扫描的表名
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperEntityColumns("id","creater","create_date","updater","update_date");
        strategy.setEntityLombokModel(true);
        mpg.setStrategy(strategy);



        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        try {
            mpg.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


	public static String handleTableName(String tableName){
           String camelTableName = NamingStrategy.underlineToCamel(tableName.replace("t_",""));
           String firstLetter = camelTableName.substring(0,1);
           return firstLetter.toUpperCase() + camelTableName.substring(1);
     }


}
