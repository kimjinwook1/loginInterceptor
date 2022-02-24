package hello.login.web.mail;

import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@RequiredArgsConstructor
@Service
public class EmailService {

	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;

	public void sendEmailMessage(String email) throws MessagingException {
		String code = createCode();
		MimeMessage message = mailSender.createMimeMessage();

		message.addRecipients(RecipientType.TO, email);
		message.setSubject("[인증 코드]" + code);
		message.setText(setContext(code), "utf-8", "html");

		//보낼 때 이름 설정하고 싶은 경우 message.setFrom(new InternetAddress([이메일 계정],[설정할 이름]));

		mailSender.send(message);
	}

	private String createCode() {
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 7; i++) {
			int index = random.nextInt(3);
			if (index == 0) {
				code.append((char) (random.nextInt(26) + 97));
			}
			if (index == 1) {
				code.append((char) (random.nextInt(26) + 65));
				code.append((char) (random.nextInt(10)));
			}
			if (index == 2) {
				code.append((char) (random.nextInt(10)));
			}
		}
		return code.toString();
	}

	private String setContext(String code) {
		Context context = new Context();
		context.setVariable("code", code);
		return templateEngine.process("mail", context);

	}


}
