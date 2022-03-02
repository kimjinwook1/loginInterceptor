package hello.login.web.oauth;

import hello.login.domain.member.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("SecurityConfig");
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeRequests()
			.antMatchers("/", "/homeLogin", "/members/add", "/css/**",
				"/images/**", "/js/**", "/h2-console/**", "/profile", "/mail/**")
			.permitAll()
			.antMatchers("/items/**").hasRole(Role.GUEST.name())
			.anyRequest().authenticated()
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.oauth2Login()
			.userInfoEndpoint()
			.userService(customOAuth2UserService);
	}
}
