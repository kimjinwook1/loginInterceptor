
package hello.login.web.login;

import hello.login.domain.login.LoginServiceV2;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class loginControllerV2 {

	private final LoginServiceV2 loginServiceV2;
	private final SessionManager sessionManager;

	@GetMapping("/homeLogin")
	public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
		log.info("loginController getmapping loginForm메서드");
		return "login/loginForm";
	}

	@PostMapping("/homeLogin")
	public String loginV4(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult,
		@RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
		log.info("loginController PostMapping loginV4메서드");
		if (bindingResult.hasErrors()) {
			return "login/loginForm";
		}

		Member loginMember = loginServiceV2.login(form.getLoginId(), form.getPassword());
		Member socialLoginMember = loginServiceV2.socialLogin(form.getEmailText);

		if (loginMember == null) {
			bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "login/loginForm";
		}

		//로그인 성공 처리
		//세선이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
		HttpSession session = request.getSession();
		//세션에 로그인 회원 정보 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		session.setAttribute(SessionConst.SOCIAL_LOGIN_MEMBER, socialLoginMember);
		return "redirect:" + redirectURL;
	}

	@PostMapping("/logout")
	public String logoutV3(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	private void expireCookie(HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
