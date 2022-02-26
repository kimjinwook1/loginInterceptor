package hello.login;

import hello.login.domain.item.Item;
import hello.login.domain.item.ItemJpaRepository;
import hello.login.domain.member.Member;
import hello.login.domain.member.MemberJpaRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemJpaRepository itemJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemJpaRepository.save(new Item("itemA", 10000, 10));
        itemJpaRepository.save(new Item("itemB", 20000, 20));

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        memberJpaRepository.save(member);
    }

}
