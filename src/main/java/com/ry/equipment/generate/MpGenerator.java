package com.ry.equipment.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


//代码自动生成器
public class MpGenerator {
    public static void main(String[] args) {
        // 代码自动生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 配置策略
        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D:\\项目开发\\equipment\\equip_back\\src\\main\\java");
        gc.setAuthor("Ry");
        gc.setOpen(false); //是否打开资源管理器
        gc.setFileOverride(false); //是否覆盖
        gc.setServiceName("%sService"); //去掉service的I前缀
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/equipment_man?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 3.配置包
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.ry.equipment");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user", "category", "equipment", "log", "rent_record", "return_record"); //设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); //自动lombok
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //执行
        mpg.execute();
    }
}
