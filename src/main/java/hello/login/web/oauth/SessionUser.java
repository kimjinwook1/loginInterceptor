package hello.login.web.oauth;

import hello.login.domain.member.Member;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
	private String name;
	private String email;
	private String picture;

	public SessionUser(Member member) {
		this.name = member.getName();
		this.email = member.getEmailText();
		this.picture = member.getPicture();
	}
}
