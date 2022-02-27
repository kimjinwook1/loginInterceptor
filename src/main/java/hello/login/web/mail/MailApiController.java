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
		log.info("member={}", mailSaveDto);
		String email = mailSaveDto.getEmailText();
		String title = "블로그 이메일 인증번호입니다.";
		String number = mailSaveDto.getNumber();
		log.info("number={}", number);
		String message = "인증번호 : " + number;
		MailTo mailTo = new MailTo(email, title, message);
		long start = System.currentTimeMillis();
		mailService.sendMail(mailTo);
		long end = System.currentTimeMillis();
		log.info("sendMail={}", (end - start));
		log.info("saveNumber={}", number);
	}

	@PostMapping("/api/v2/posts")
	public void checkNumber(@Validated @RequestBody InputNumber inputNumber) {
		log.info("checkNumber");
		log.info("Member={}", inputNumber.getCertificationNumber());
		Integer certificationNumber = Integer.valueOf(inputNumber.getNumber());
		log.info("certificationNumber={}", certificationNumber);
		if (!inputNumber.getCertificationNumber().equals(certificationNumber)) {
			throw new IllegalStateException("인증번호가 일치하지 않습니다.");
		}
	}
}
