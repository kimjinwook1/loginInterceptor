package hello.login.web.oauth;

import hello.login.web.security.common.FormAuthenticationDetailsSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final FormAuthenticationDetailsSource authenticationDetailsSource;
	private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final AuthenticationFailureHandler customAuthenticationFailureHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("SecurityConfig");
		http
			.authorizeRequests()
			.antMatchers("/", "/homeLogin", "/members/add", "/profile", "/mail/**")
			.permitAll()
			.antMatchers("/items/**").hasRole("GUEST")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/homeLogin")
			.loginProcessingUrl("/login_proc")
			.authenticationDetailsSource(authenticationDetailsSource)
			.defaultSuccessUrl("/")
			.successHandler(customAuthenticationSuccessHandler)
			.failureHandler(customAuthenticationFailureHandler)
			.permitAll()
			.and()
			.oauth2Login()
			.loginPage("/homeLogin")
			.defaultSuccessUrl("/")
			.failureUrl("/homeLogin")
			.userInfoEndpoint()
			.userService(customOAuth2UserService);
	}
}
