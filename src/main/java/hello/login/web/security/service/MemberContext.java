package hello.login.web.security.service;

import hello.login.domain.member.Member;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberContext extends User {

	private final Member member;

	public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
		super(member.getUsername(), member.getPassword(), authorities);
		this.member = member;
	}

	public Member getMember() {
		return member;
	}
}
