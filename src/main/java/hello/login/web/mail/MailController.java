package hello.login.web.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

	private final MailService mailService;

	@GetMapping("/send")
	public MailTo sendTestMail(String email) {
		String title = "제목 인증번호입니다.";
		String message = "인증번호입니다.";
		MailTo mailTo = new MailTo(email, title, message);
		mailService.sendMail(mailTo);

		return mailTo;
	}

	@GetMapping("/send/file")
	public MailTo sendTestFileEmail(String email) throws Exception{
		String title = "제목 인증번호입니다.";
		String message = "인증번호입니다.";
		MailTo mailTo = new MailTo(email, title, message);
		mailService.sendMailWithFiles(mailTo);

		return mailTo;
	}
}
