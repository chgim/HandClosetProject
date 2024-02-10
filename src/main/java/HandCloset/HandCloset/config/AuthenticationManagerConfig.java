package HandCloset.HandCloset.config;


import HandCloset.HandCloset.security.jwt.filter.JwtAuthenticationFilter;
import HandCloset.HandCloset.security.jwt.provider.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class AuthenticationManagerConfig extends AbstractHttpConfigurer<AuthenticationManagerConfig, HttpSecurity> {

    private final JwtAuthenticationProvider jwtAuthenticationProvider; // 주입

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class); // 빌더를 통해서 AuthenticationManager 생성

        builder.addFilterBefore(
                        new JwtAuthenticationFilter(authenticationManager),
                        UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(jwtAuthenticationProvider);
    }
    // UsernamePasswordAuthenticationFilter 앞에 JwtAuthenticationFilter을 두겠다. JwtAuthenticationFilter는 authenticationManager를 가지도록 하겠다. Provider에는 jwtAuthenticationProvider 사용하도록 설정.
}