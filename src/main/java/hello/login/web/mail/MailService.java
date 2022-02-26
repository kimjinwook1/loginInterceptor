package hello.login.web.mail;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {

	private final JavaMailSender mailSender;

	@Async
	public void sendMail(MailTo mail) {
		log.info("MailService.sendMail");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getAddress());
		message.setSubject(mail.getTitle());
		message.setText(mail.getMessage());
		mailSender.send(message);
	}

	public void sendMailWithFiles(MailTo mail) throws Exception{
		MailHandler mailHandler = new MailHandler(mailSender);
		mailHandler.setTo(mail.getAddress());
		mailHandler.setSubject(mail.getTitle());

		String htmlContent = "<p>" + mail.getMessage() + "<p><img src ='cid:google-logo'>";
		mailHandler.setText(htmlContent, true);
		mailHandler.setAttach("test.txt", "static/test.txt");
		mailHandler.setInline("goole-logo", "static/google-logo.png");
		mailHandler.send();
	}

	public String getRandomNumber() {
		Random random = new Random();
		StringBuilder aa = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int number = random.nextInt(9) + 1;
			aa.append(number);
		}
		return aa.toString();
	}

}
