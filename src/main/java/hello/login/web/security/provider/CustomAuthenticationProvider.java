package hello.login.web.security.provider;

import hello.login.web.security.common.FormWebAuthenticationDetails;
import hello.login.web.security.service.MemberContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
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
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(username);

		if (!passwordEncoder.matches(password, memberContext.getMember().getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}

		FormWebAuthenticationDetails details = (FormWebAuthenticationDetails) authentication.getDetails();
		String secretKey = details.getSecretKey();
		if (secretKey == null || !"secret".equals(secretKey)) {
			throw new InsufficientAuthenticationException("InsufficientAuthenticationException");
		}

		return new UsernamePasswordAuthenticationToken(
			memberContext.getMember(), null, memberContext.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
