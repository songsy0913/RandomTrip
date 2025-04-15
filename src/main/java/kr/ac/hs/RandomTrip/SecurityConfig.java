package kr.ac.hs.RandomTrip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf)-> csrf.disable()); //csrf 끄기


        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
        );

        http.formLogin(form -> form.disable()); // ✅ HTTP Basic 인증 비활성화
        http.httpBasic(basic -> basic.disable());

//        http.formLogin((formLogin)
//                        -> formLogin.loginPage("/login")
//                        .defaultSuccessUrl("/")
////                .failureUrl("/fail")
//        );


        http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));


        return http.build();
    }
}
