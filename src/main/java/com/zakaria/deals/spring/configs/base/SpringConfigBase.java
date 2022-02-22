package com.zakaria.deals.spring.configs.base;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.logviewer.springboot.LogViewerSpringBootConfig;


/**
 * @author Zakaria Alyafawi
 * @version 1.0
 * @since 2022-02-18
 */
//@EnableWebSecurity
@Configuration
@EnableAutoConfiguration
@Import({ LogViewerSpringBootConfig.class})
@SpringBootApplication
@ComponentScan(basePackages = "com.zakaria.deals")
//@EnableWebMvc
public class SpringConfigBase {
}
