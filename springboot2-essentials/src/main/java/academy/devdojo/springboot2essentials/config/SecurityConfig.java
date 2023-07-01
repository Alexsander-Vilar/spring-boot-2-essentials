package academy.devdojo.springboot2essentials.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Log4j2
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    /**
     * BasicAuthenticationFilter
     * UsernamePasswordAuthenticationFilter
     * DefaultLoginPageGeneratingFilter
     * DefaultLogoutPageGeneratingFilter
     * FilterSecurityInterceptor
     * Authentication -> Authorization
     *
     * @param http
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
//                .csrf((csrf) -> csrf
//                 .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/animes/admin/**").hasRole("ADMIN")
                        .requestMatchers("/animes/**").hasRole("USER")
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        return http.build();
    }

        @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails userAdm = User.withUsername("alexsander")
                .password(encoder.encode("academy"))
                .roles("USER","ADMIN")
                .build();
        UserDetails user = User.withUsername("devdojo")
                .password(encoder.encode("academy"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, userAdm);
    }
}



