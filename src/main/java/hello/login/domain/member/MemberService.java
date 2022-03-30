package hello.login.domain.member;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

	private final MemberJpaRepository memberJpaRepository;

	@Transactional
	public Member save(Member member) {
		return memberJpaRepository.save(member);
	}

	public Member findById(Long id) {
		return memberJpaRepository.findById(id).get();
	}

	public Optional<Member> findByLoginId(String loginId) {
		return memberJpaRepository.findAll().stream()
			.filter(m -> m.getUsername().equals(loginId))
			.findFirst();
	}

	public List<Member> findAll() {
		return memberJpaRepository.findAll();
	}
}
