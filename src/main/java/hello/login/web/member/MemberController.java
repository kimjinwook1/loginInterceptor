package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.mail.MailSaveDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

	private final MemberRepository memberRepository;

	@GetMapping("/add")
	public String addForm(@ModelAttribute("member") Member member, @ModelAttribute("mailSaveDto")MailSaveDto mailSaveDto) {
		log.info("MemberController에 getmapping save메서드");
		return "members/addMemberForm";
	}


	@PostMapping("/add")
	public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
		log.info("MemberController에 postmapping save메서드");
		log.info("Member={}",member);
		if (bindingResult.hasErrors()) {
			return "members/addMemberForm";
		}
		memberRepository.save(member);
		return "redirect:/homeLogin";
	}

}
