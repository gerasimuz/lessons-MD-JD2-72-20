package com.github.nastyasivko.project_final.service.config;

import com.github.nastyasivko.project_final.dao.config.DaoConfig;
import com.github.nastyasivko.project_final.service.SecurityLoginUser;
import com.github.nastyasivko.project_final.service.impl.DefaultSecurityLoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public SecurityLoginUser securityLoginUser() {
        return new DefaultSecurityLoginUser(daoConfig.loginUserDao());
    }
}
