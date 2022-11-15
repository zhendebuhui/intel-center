package com.fk.core;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

/**
 * Author Yjw
 * 2022/11/10 23:53
 */
public class CodeGenerator {

    @Test
    public void genCode() {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setIdType(IdType.AUTO);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/intel_center?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.fk.core");
        pc.setEntity("pojo.entity");
        mpg.setPackageInfo(pc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setLogicDeleteFieldName("is_deleted");
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        mpg.execute();
    }

}
