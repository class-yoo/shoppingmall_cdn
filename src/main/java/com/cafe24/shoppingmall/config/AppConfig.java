package com.cafe24.shoppingmall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.cafe24.shoppingmall.security", "com.cafe24.shoppingmall.service",
		"com.cafe24.shoppingmall.repository"})
public class AppConfig {
}