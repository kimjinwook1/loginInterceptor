package hello.login.web.mail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class MailSaveDto {
	@NotNull
	@Email
	private String emailText;
}
