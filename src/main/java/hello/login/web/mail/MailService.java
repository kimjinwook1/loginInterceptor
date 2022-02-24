package hello.login.web.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender mailSender;

	public void sendMail(MailTo mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getAddress());
		//message.setFrom(""); -> from 값을 설정하지 않으면 application.properties에 username 값이 설정된다.
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

}
