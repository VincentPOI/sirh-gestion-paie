package dev.paie.config;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Import(WebAppConfig.class)
@ImportResource("classpath:jdd-config.xml")
public class TestConfig {

}
