package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.web.security.service.MemberContext;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeControllerV2 {

	@GetMapping("/test")
	public String test2() {
		return "test";
	}

//	@GetMapping("/login")
	@ResponseBody
	public String test() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		OAuth2User principal = (OAuth2User) authentication.getPrincipal();
//		Map<String, Object> attributes = principal.getAttributes();
//		System.out.println(attributes.get("picture") == null
//			? (String) attributes.get("avatar_url") : (String) attributes.get("picture"));
//
//		String email = (String) attributes.get("email");
		return "test";
	}

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
