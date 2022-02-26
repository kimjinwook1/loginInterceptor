package hello.login.web.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MailController {

	private final MailService mailService;

	@GetMapping(value = "/mail")
	public String sendMail(@ModelAttribute("mail") MailDto mailDto) {
		return "mail/mail";
	}

	@GetMapping("/posts/save")
	public String postsSave(Model model) {
		model.addAttribute("MailSaveDto", new MailSaveDto());
		return "posts-save";
	}

	@ResponseBody
	@PostMapping("/send")
	public MailTo sendTestMail(@RequestBody MailSaveDto mailSaveDto) {
		String email = mailSaveDto.getEmailText();
		log.info("email={}", email);
		log.info("send Controller 실행");
		String title = "제목 인증번호입니다.";
		String message = "인증번호입니다.";
		MailTo mailTo = new MailTo(email, title, message);
		log.info("email ={}", email);
		mailService.sendMail(mailTo);
		return mailTo;
	}

	@ResponseBody
	@GetMapping("/send/file")
	public MailTo sendTestFileEmail(String email) throws Exception{
		String title = "제목 인증번호입니다.";
		String message = "인증번호입니다.";
		MailTo mailTo = new MailTo(email, title, message);
		mailService.sendMailWithFiles(mailTo);

		return mailTo;
	}
}
