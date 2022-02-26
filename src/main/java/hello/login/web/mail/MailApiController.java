package hello.login.web.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MailApiController {
	private final MailService mailService;

	@PostMapping("/api/v1/posts")
	public void save(@Validated @RequestBody MailSaveDto mailSaveDto) {
		log.info("MailApiController");
		log.info("member={}",mailSaveDto);
		String email = mailSaveDto.getEmailText();
		String title = "제목 인증번호입니다.";
		String number = mailService.getRandomNumber();
		String message = "인증번호 : " + number;
		MailTo mailTo = new MailTo(email, title, message);
		long start = System.currentTimeMillis();
		mailService.sendMail(mailTo);
		long end = System.currentTimeMillis();
		log.info("sendMail={}", (end - start));
		log.info("saveNumber={}", number);
	}

	@PostMapping("/api/v2/posts")
	public Long check(@RequestBody MailSaveDto mailSaveDto) {
//		Integer certificationNumber = mailSaveDto.getCertificationNumber();
		String randomNumber = mailService.getRandomNumber();
		log.info("checkNumber={}", randomNumber);
		return 0L;
	}


}
