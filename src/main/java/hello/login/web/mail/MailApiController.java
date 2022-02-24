package hello.login.web.mail;

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
//		System.out.println("requestDto = " + requestDto);
		log.info("mailSaveDto={}",mailSaveDto);
		String email = mailSaveDto.getTitle();
		log.info("email={}", email);
		log.info("send Controller 실행");
		String title = "제목 인증번호입니다.";
		String message = "인증번호입니다.";
		MailTo mailTo = new MailTo(email, title, message);
		log.info("email ={}", email);
		mailService.sendMail(mailTo);
		return 0L;
	}
}
