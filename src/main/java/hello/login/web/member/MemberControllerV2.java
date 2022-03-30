package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberJpaRepository;
import hello.login.domain.member.Role;
import hello.login.web.mail.MailSaveDto;
import hello.login.web.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberControllerV2 {

	private final MemberJpaRepository memberJpaRepository;
	private final MailService mailService;

	@GetMapping("/add")
	public String addForm(@ModelAttribute("member") Member member, @ModelAttribute("mailSaveDto") MailSaveDto mailSaveDto, Model model) {
		log.info("MemberController에 getmapping save메서드");
		String number = mailService.getRandomNumber();
		model.addAttribute("number", number);
		return "members/addMemberForm";
	}

	@PostMapping("/add")
	public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult, Model model) {
		log.info("MemberController에 postmapping save메서드");
		log.info("Member={}", member);
		member.setRole(Role.ROLE_GUEST);
		if (bindingResult.hasErrors()) {
			return "members/addMemberForm";
		}
		memberJpaRepository.save(member);
		return "redirect:/homeLogin";
	}

}
