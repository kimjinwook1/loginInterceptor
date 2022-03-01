package hello.login.domain.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByLoginId(String loginId);
	Optional<Member> findByEmailText(String emailText);
}
