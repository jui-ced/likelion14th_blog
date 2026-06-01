package likelion14th.blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    @NotBlank(message="제목은 필수입니다.")
    @Size(min=1, max=30, message="제목은 1자 이상 30자 이하로 입력하세요.")
    private String title;

    @NotBlank(message="내용은 필수입니다.")
    @Size(min=1, message = "내용은 1자 이상 입력하세요.")
    private String content;

    @NotBlank(message = "작성자는 필수입니다.")
    @Size(min=1,max=10, message = "작성자는 1자 이상 10자 이하로 입력하세요.")
    private String author;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min=1, max=20, message = "비밀번호는 1~20자 사이어야 합니다.")
    private String password;
}
