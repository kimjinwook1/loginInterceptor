package hello.login.web.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

	public String getEmailText;

    private String loginId;

    private String password;
}
