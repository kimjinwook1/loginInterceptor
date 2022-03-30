package hello.login.domain.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByUsername(String username);
	Optional<Member> findByEmailText(String emailText);
}
