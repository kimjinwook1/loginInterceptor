package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceV2 {

    private final MemberJpaRepository memberJpaRepository;

    /**
     *
     * @return null 로그인 실패
     */
    public Member login(String username, String password) {

         return memberJpaRepository.findByUsername(username)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);

    }

	public Member socialLogin(String emailText) {

		return memberJpaRepository.findByEmailText(emailText)
			.filter(m -> m.getEmailText().equals(emailText))
			.orElse(null);
	}
}

