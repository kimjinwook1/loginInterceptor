package hello.login.web.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MailApiController {

	private final MailService mailService;

	@RequestMapping("/api/v1/posts")
	public void save(@Validated @RequestBody MailSaveDto mailSaveDto) throws Exception {
		log.info("MailApiController");
		log.info("member={}", mailSaveDto);
		String email = mailSaveDto.getEmailText();
		String title = "블로그 이메일 인증번호입니다.";
		String number = mailSaveDto.getNumber();
		log.info("number={}", number);
		String message = "인증번호 : " + number;
		MailTo mailTo = new MailTo(email, title, message);
		mailService.sendMail(mailTo);
		log.info("saveNumber={}", number);
		long currentTimeMillis = System.currentTimeMillis();
		mailService.saveSendMailTime(currentTimeMillis);
		System.out.println("currentTimeMillis = " + currentTimeMillis);
	}

	@PostMapping("/api/v2/posts")
	public void checkNumber(@Validated @RequestBody InputNumber inputNumber) {
		log.info("checkNumber");
		log.info("Member={}", inputNumber.getCertificationNumber());
		Integer certificationNumber = Integer.valueOf(inputNumber.getNumber());
		log.info("certificationNumber={}", certificationNumber);
		long sendMailTime = mailService.getSendMailTime();
		System.out.println("sendMailTime = " + sendMailTime);
		long limitTIme = 1000 * 1 * 10;//10초
		if (System.currentTimeMillis() > (sendMailTime + (limitTIme * 1))) {
			throw new IllegalStateException("인증시간이 초과하였습니다.");
		}
		if (!inputNumber.getCertificationNumber().equals(certificationNumber)) {
			throw new IllegalStateException("인증번호가 일치하지 않습니다.");
		}
	}
}
