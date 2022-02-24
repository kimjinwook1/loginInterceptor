package hello.login.domain.member;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id; //DB 에 보관되는 ID

    @NotEmpty
    private String loginId; //로그인 ID
    @NotEmpty
    private String name; //사용자 이름
    @NotEmpty
    private String password;
    @NotEmpty
    private String email; //사용자 이름
    private String certificationNumber;
}
