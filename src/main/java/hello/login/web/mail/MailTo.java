package hello.login.web.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailTo {

	private String address;
	private String title;
	private String message;
}
