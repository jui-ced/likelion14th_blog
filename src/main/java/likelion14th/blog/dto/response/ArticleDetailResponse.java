package likelion14th.blog.dto.response;

import likelion14th.blog.domain.Article;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Builder
public class ArticleDetailResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;

    public static ArticleDetailResponse from(Article article) {
        return ArticleDetailResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor())
                .createdAt(article.getCreatedAt())
                .build();
    }
}


