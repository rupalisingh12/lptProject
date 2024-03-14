package com.leanplatform.MentorshipPlatform.security;



import com.leanplatform.MentorshipPlatform.filter.JwtAuthFilter;
import com.leanplatform.MentorshipPlatform.services.JwtService;
import com.leanplatform.MentorshipPlatform.services.UserService;
//import com.leanplatform.MentorshipPlatform.services.implementation.User.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtAuthFilter authFilter;

    @Autowired
    @Lazy
    UserService userService;

//    @Autowired
//    CustomOAuth2UserService oAuth2UserService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        auth
                .inMemoryAuthentication()
                .withUser("ADMIN321")
                .password(passwordEncoder.encode("adminPassword"))
                .authorities("ROLE_ADMIN","ROLE_USER");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize)->
                        authorize
                                .requestMatchers("/*","/*/*","/*/*/*","/*/*/*/*").permitAll()

                )
                .cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login().userInfoEndpoint().userService(oAuth2UserService).and().successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//                        try {
//                            DefaultOidcUser oidcUser= (DefaultOidcUser) authentication.getPrincipal();
//                            userService.processOAuthPostLogin(oidcUser.getAttribute("email"),oidcUser.getAttribute("name"),oidcUser.getName());
//                            String token=jwtService.generateToken(oidcUser.getName());
//                            //System.out.println(token);
//
//                            response.sendRedirect("/oAuthSuccess?token="+token);
//                        }
//                        catch (Exception e){
//                            throw new RuntimeException("Error!");
//                        }
//
//                    }
//                })
                ;

        return http.build();
    }


//    .formLogin(
//            form-> form.permitAll()
//            .successHandler(new AuthenticationSuccessHandler() {
//        @Override
//        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//            User user=(User) authentication.getPrincipal();
//            String username=user.getFirstName();
//
//            response.sendRedirect(request.getContextPath());
//        }
//    })
//
//            )
//            .logout(
//            logout-> logout
//            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//            .permitAll()
//                )


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    public FilterRegistrationBean corsFilter(){

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("Authorization");
        corsConfiguration.addAllowedHeader("Content-Type");
        corsConfiguration.addAllowedHeader("Accept");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.setMaxAge(3600L);

        source.registerCorsConfiguration("/**",corsConfiguration);



        FilterRegistrationBean bean=new FilterRegistrationBean(new CorsFilter(source));
        return bean;
    }


}
