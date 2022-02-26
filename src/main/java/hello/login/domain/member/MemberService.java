package hello.login.domain.member;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberJpaRepository memberJpaRepository;

	public Member save(Member member) {
		return memberJpaRepository.save(member);
	}

	public Member findById(Long id) {
		return memberJpaRepository.findById(id).get();
	}

	public Optional<Member> findByLoginId(String loginId) {
		return memberJpaRepository.findByLoginId(loginId).stream()
			.filter(m -> m.getLoginId().equals(loginId))
			.findFirst();
	}

	public List<Member> findAll() {
		return memberJpaRepository.findAll();
	}
}
