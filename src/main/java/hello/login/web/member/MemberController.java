package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository2;
import hello.login.web.mail.MailSaveDto;
import hello.login.web.mail.MailService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

	private final MemberRepository2 memberRepository2;

	@GetMapping("/add")
	public String addForm(@ModelAttribute("member") Member member, @ModelAttribute("mailSaveDto")MailSaveDto mailSaveDto) {
		log.info("MemberController에 getmapping save메서드");
		return "members/addMemberForm";
	}


	@PostMapping("/add")
	public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
		log.info("MemberController에 postmapping save메서드");
		if (bindingResult.hasErrors()) {
			return "members/addMemberForm";
		}
		memberRepository2.save(member);
		return "redirect:/login";
	}
//
//	private final JavaMailSender javaMailSender;
//
//	@ResponseBody
//	@PostMapping("/api/member/email")
//	public int sendEmail(HttpServletRequest request, String userEmail) throws Exception {
//		HttpSession session = request.getSession();
//		mailSend(session, userEmail);
//		return 123;
//	}
//
//	@ResponseBody
//	@PostMapping("/api/member/email/certification")
//	public boolean emailCertification(HttpServletRequest request, String userEmail, String inputCode) {
//		HttpSession session = request.getSession();
//
//		return emailCertification(session, userEmail, Integer.parseInt(inputCode));
//	}
//
//	public boolean emailCertification(HttpSession session, String userEmail, int inputCode) {
//		int generationCode = (int) session.getAttribute(userEmail);
//		return generationCode == inputCode;
//	}
//
//	public void mailSend(HttpSession session, String userEmail) throws Exception {
//		MailHandler mailHandler = new MailHandler(javaMailSender);
//		Random random = new Random(System.currentTimeMillis());
//		long start = System.currentTimeMillis();
//
//		int result = 100000 + random.nextInt(900000);
//
//		//받는 사람
//		mailHandler.setTo(userEmail);
//		//보내는 사람
//		mailHandler.setFrom("jinwook628@gmail.com");
//		//제목
//		mailHandler.setSubject("인증번호입니다.");
//		//Html Layout
//		String htmlContent = "<p> 인증번호: + " + result + "<p>";
//		mailHandler.setText(htmlContent, true);
//
//		mailHandler.send();
//
//		long end = System.currentTimeMillis();
//
//		session.setAttribute("" + userEmail, result);
//		log.info("실행시간={}", (end - start) / 1000.0);
//	}

}
