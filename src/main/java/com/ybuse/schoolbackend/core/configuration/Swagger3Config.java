package com.ybuse.schoolbackend.core.configuration;

import com.ybuse.schoolbackend.core.annotation.swagger.Mark;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootConfiguration
public class Swagger3Config implements BeanPostProcessor {

    /**
     * 判断是否已经扫描包
     */
    private static boolean isScan = false;
    final ConfigurableListableBeanFactory beanFactory;
    @Value("${spring.api-docs.default-group-name}")
    private final String defaultGroupName = "default";

    public Swagger3Config(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * bean初始化之后调用
     *
     * @param bean     the new bean instance
     * @param beanName the name of the bean
     * @return bean
     * @throws BeansException if thrown by the bean factory methods
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (isScan) {
            return bean;
        }
        // 获取被标记的包
        Map<String, List<Package>> packages = new HashMap<>();
        for (Package aPackage : Package.getPackages()) {
            Mark mark = aPackage.getAnnotation(Mark.class);
            if (mark != null) {
                addPackages(packages, mark, aPackage);
            }
        }
        // 注册API说明
        packages.forEach((key, value) -> {
            GroupedOpenApi.Builder group = GroupedOpenApi.builder().group(key);
            String[] paths = value.stream().map(Package::getName).toArray(String[]::new);
            group.packagesToScan(paths);
            beanFactory.registerSingleton(key + "Api", group.build());
        });
        isScan = true;
        return bean;
    }

    @Bean
    public GroupedOpenApi testApi() {
        return GroupedOpenApi.builder()
                .group("测试api")
                .packagesToScan("com.ybuse.schoolbackend")
                .pathsToMatch("/**/test")
                .build();
    }

    /**
     * 添加包
     *
     * @param packages 包列表
     * @param mark     注解对象
     * @param aPackage 被添加的包
     */
    private void addPackages(Map<String, List<Package>> packages, Mark mark, Package aPackage) {
        if (packages.containsKey(mark.value())) {
            packages.get(mark.value()).add(aPackage);
        } else {
            String key = mark.value().equals("") ? defaultGroupName : mark.value();
            ArrayList<Package> list = new ArrayList<>();
            list.add(aPackage);
            packages.put(key, list);
        }
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("School Backend API")
                        .description("RESTful风格后端API")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                ).externalDocs(new ExternalDocumentation().description("Swagger3(OpenAPI)常用注解参考").url("https://blog.csdn.net/qq_35425070/article/details/105347336"));
    }

}
