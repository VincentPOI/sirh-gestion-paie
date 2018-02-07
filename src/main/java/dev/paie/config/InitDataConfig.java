package dev.paie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:grades.xml", "classpath:profils-remuneration.xml", "classpath:entreprises.xml",
		"classpath:cotisations-imposables.xml", "classpath:cotisations-non-imposables.xml",
		"classpath:utilisateurs.xml" })
public class InitDataConfig {
}
