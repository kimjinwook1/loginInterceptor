package hello.login.web.mail;

import org.springframework.stereotype.Repository;

@Repository
public class MailRepository {

	private final MailSendTime mailSendTime = new MailSendTime();

	public void saveTime(long currentTimeMillis) {
		mailSendTime.setTime(currentTimeMillis);
	}

	public long getTime() {
		return mailSendTime.getTime();
	}
}
