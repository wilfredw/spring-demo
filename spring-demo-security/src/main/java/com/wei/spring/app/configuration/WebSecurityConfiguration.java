package com.wei.spring.app.configuration;

import com.wei.spring.app.security.MyAuthenticationFailHandler;
import com.wei.spring.app.security.MyAuthenticationSuccessHandler;
import com.wei.spring.app.service.DummyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by viruser on 2019/7/27.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DummyUserDetailsService userDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/security/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin().loginPage("/security/login")
                .loginProcessingUrl("/security/auth/login")
//                .formLogin().loginPage("/login")
//                .loginProcessingUrl("/auth/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                // 设置登陆success页
//                .defaultSuccessUrl("/security/").permitAll()
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/security/logout")
                .deleteCookies()
                .invalidateHttpSession(true)
                .permitAll();
        // 关闭CSRF跨域
        httpSecurity.csrf().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        webSecurity.ignoring().antMatchers("/css/**", "/js/**");
    }
}
