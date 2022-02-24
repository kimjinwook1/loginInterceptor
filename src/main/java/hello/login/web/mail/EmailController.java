//package hello.login.web.mail;
//
//import java.util.Map;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Controller
//@RequiredArgsConstructor
//public class EmailController {
//
//	private final EmailService emailService;
//
//	@PostMapping("/email") // 이메일 인증 코드 보내기
//	public ResponseDto<String> emailAuth(@RequestBody Map<String, String> email) throws Exception {
//		emailService.sendSimpleMessage(email.get("email"));
//
//		return new ResponseDto<>(true);
//	}
//
//	@PostMapping("/verifyCode") // 이메일 인증 코드 검증
//	public ResponseDto<?> verifyCode(@RequestBody Map<String, String> code) {
//		if(EmailService.ePw.equals(code.get("code"))) {
//			return new ResponseDto<>(true);
//		}
//		else{
//			return new ResponseDto<>(false);
//		}
//	}
//}
