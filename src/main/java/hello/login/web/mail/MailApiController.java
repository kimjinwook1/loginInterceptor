package hello.login.web.mail;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MailApiController {

	private final MailService mailService;

	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody MailSaveDto mailSaveDto) {
		long start = System.currentTimeMillis();
		String email = mailSaveDto.getEmailText();
		String title = "제목 인증번호입니다.";
		String number = checkNumber();

		String message = "인증번호 : " + number;

		MailTo mailTo = new MailTo(email, title, message);
		log.info("email ={}", email);
		mailService.sendMail(mailTo);
		long end = System.currentTimeMillis();
		System.out.println("end-start = " + (end - start));
		return 0L;
	}

	private String checkNumber() {
		Random random = new Random();
		StringBuilder aa = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int number = random.nextInt(9) + 1;
			aa.append(number);
		}
		return aa.toString();
	}
}
