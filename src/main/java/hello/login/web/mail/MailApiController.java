package hello.login.web.mail;

import hello.login.domain.member.MemberService;
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
	private final MemberService memberService;

	@PostMapping("/mail/send")
	public void save(@Validated @RequestBody MailSaveDto mailSaveDto) throws Exception {
		log.info("메일 발송");
		memberService.findAll().stream()
			.filter(member -> member.getEmailText().equals(mailSaveDto.getEmailText())).forEach(member -> {
			throw new IllegalStateException("이미 존재하는 이메일입니다.");
		});
		MailTo mailTo = getMailTo(mailSaveDto);
		mailService.sendMail(mailTo);
		mailService.saveSendMailTime(System.currentTimeMillis());
	}

	@PostMapping("/mail/check")
	public void checkNumber(@Validated @RequestBody InputNumber inputNumber) {
		log.info("인증번호 확인");
		Integer certificationNumber = Integer.valueOf(inputNumber.getNumber());
		long sendMailTime = mailService.getSendMailTime();
		long limitTIme = 1000 * 1 * 60;//60초
		if (System.currentTimeMillis() > (sendMailTime + (limitTIme * 10))) {
			throw new IllegalStateException("인증시간이 초과하였습니다.");
		}
		if (!inputNumber.getCertificationNumber().equals(certificationNumber)) {
			throw new IllegalStateException("인증번호가 일치하지 않습니다.");
		}
	}

	private MailTo getMailTo(MailSaveDto mailSaveDto) {
		String email = mailSaveDto.getEmailText();
		String title = "블로그 이메일 인증번호입니다.";
		String number = mailSaveDto.getNumber();
		String message = "인증번호 : " + number;
		return new MailTo(email, title, message);
	}
}
