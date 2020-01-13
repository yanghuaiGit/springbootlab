//package com.yh.lettue.config.mybatis;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.type.TypeHandlerRegistry;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author huaiyang
// * @version 1.0.0
// * @date 2020/1/13
// * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
// */
//@Configuration
//@ConfigurationProperties(prefix = "mybatis")
//public class MyBatisConfig {
//    private String configLocation;
//
//    private String[] mapperLocations;
//
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(
//            DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//        factory.setDataSource(dataSource);
//
//
//        // 设置配置文件及mapper文件地址
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        if (StringUtils.hasText(configLocation)) {
//            factory.setConfigLocation(resolver.getResource(configLocation));
//        }
//
//        if (!ObjectUtils.isEmpty(this.resolveMapperLocations())) {
//            factory.setMapperLocations(this.resolveMapperLocations());
//        }
//        SqlSessionFactory sqlSessionFactory = factory.getObject();
//
//
//        // 取得类型转换注册器
//        TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
//        // 注册默认枚举转换器
//        typeHandlerRegistry.setDefaultEnumTypeHandler(AutoEnumTypeHandler.class);
//
//        return sqlSessionFactory;
//    }
//
//    public Resource[] resolveMapperLocations() {
//        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
//        List<Resource> resources = new ArrayList();
//        if (this.mapperLocations != null) {
//            String[] var3 = this.mapperLocations;
//            int var4 = var3.length;
//
//            for(int var5 = 0; var5 < var4; ++var5) {
//                String mapperLocation = var3[var5];
//
//                try {
//                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
//                    resources.addAll(Arrays.asList(mappers));
//                } catch (IOException var8) {
//                }
//            }
//        }
//
//        return (Resource[])resources.toArray(new Resource[resources.size()]);
//    }
//
//    public String getConfigLocation() {
//        return configLocation;
//    }
//
//    public void setConfigLocation(String configLocation) {
//        this.configLocation = configLocation;
//    }
//
//    public String[] getMapperLocations() {
//        return mapperLocations;
//    }
//
//    public void setMapperLocations(String[] mapperLocations) {
//        this.mapperLocations = mapperLocations;
//    }
//}
