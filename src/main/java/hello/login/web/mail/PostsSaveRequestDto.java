package hello.login.web.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//안쓰는 데이터
@Getter
@NoArgsConstructor
@ToString
public class PostsSaveRequestDto {

	private String title;
	private String content;
	private String author;

	@Builder
	public PostsSaveRequestDto(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
}
