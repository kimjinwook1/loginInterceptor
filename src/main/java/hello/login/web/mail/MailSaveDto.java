package hello.login.web.mail;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class MailSaveDto {
	private String emailText;
	private Integer certificationNumber;
}
