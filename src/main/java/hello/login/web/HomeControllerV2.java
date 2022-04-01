package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.web.security.service.MemberContext;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeControllerV2 {

	@GetMapping("/")
	public String homeLoginV3ArgumentResolver(HttpSession session, Model model,
		@AuthenticationPrincipal MemberContext memberContext) {

		Member loginSocial = (Member) session.getAttribute(SessionConst.SOCIAL_LOGIN_MEMBER);

		if (memberContext == null && loginSocial == null) {
			return "home";
		}
		if (memberContext == null) {
			model.addAttribute("member", loginSocial);
			return "loginHome";
		}
		if (loginSocial == null) {
			model.addAttribute("member", memberContext);
			return "loginHome";
		}
		return "home";
	}
}
