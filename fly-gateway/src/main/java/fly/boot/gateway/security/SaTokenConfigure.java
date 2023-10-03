package fly.boot.gateway.security;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.http.HttpStatus;
import fly.boot.gateway.config.WhiteProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SaTokenConfigure {

    @Resource
    private WhiteProperties whiteProperties;
    @Bean
    public SaReactorFilter saReactorFilter() {
        return new SaReactorFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico", "/actuator/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**")
                        .setAuth(r -> {
                            SaRouter.match("/**")
                                    .notMatch(whiteProperties.getWhites())
                                    .check(u -> {
                                        StpUtil.checkLogin();
                                    });
                        })
                        .setError(e -> SaResult.error("认证失败，请重新登录").setCode(HttpStatus.HTTP_UNAUTHORIZED));

    }
}
