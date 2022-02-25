package hello.login.web.mail;

import hello.login.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MailApiController {
	private final MailService mailService;

	@PostMapping("/api/v1/posts")
	public String save(@RequestBody Member mailSaveDto) {
		log.info("MailApiController");
		log.info("member={}",mailSaveDto);
		long start = System.currentTimeMillis();
		String email = mailSaveDto.getEmailText();
		String title = "제목 인증번호입니다.";
		String number = mailService.getRandomNumber();
		String message = "인증번호 : " + number;
		MailTo mailTo = new MailTo(email, title, message);
		mailService.sendMail(mailTo);
		mailSaveDto.setCertificationNumber(Integer.valueOf(number));
		long end = System.currentTimeMillis();
		log.info("saveNumber={}", number);
		log.info("loadTime={}", (end - start));
		return "redirect:/members/add";
	}

	@PostMapping("/api/v2/posts")
	public Long check(@RequestBody MailSaveDto mailSaveDto) {
		Integer certificationNumber = mailSaveDto.getCertificationNumber();
		String randomNumber = mailService.getRandomNumber();
		log.info("checkNumber={}", randomNumber);
		return 0L;
	}


}
