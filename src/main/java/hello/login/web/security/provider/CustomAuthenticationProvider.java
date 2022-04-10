package hello.login.web.security.provider;

import hello.login.web.security.service.MemberContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//검증을 위한 구현
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername("test");

		if (!passwordEncoder.matches(password, memberContext.getMember().getPassword())) {
			throw new BadCredentialsException("비밀번호가 일치 하지 않습니다.");
		}

		// 넘겨주는 객체를 기준으로 컨트롤러에서
		// @AuthenticationPrincipal MemberContext memberContext
		// 이런 식으로 받아 올 수 있음
		return new UsernamePasswordAuthenticationToken(
			memberContext, null, memberContext.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 파라미터로 전달되는 authentication 클래스 타입과
		// CustomAuthenticationProvider 가 사용하고자 하는 토큰의 타입이 일치할 때
		// 이 provider 인증 처리를 할 수 있도록 조건 처리
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
