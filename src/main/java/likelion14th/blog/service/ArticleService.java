package likelion14th.blog.service;

import jakarta.persistence.EntityNotFoundException;
import likelion14th.blog.domain.Article;
import likelion14th.blog.dto.response.ArticleDetailResponse;
import likelion14th.blog.dto.response.ArticleSummaryResponse;
import likelion14th.blog.exception.ArticleNotFoundException;
import likelion14th.blog.exception.*;
import likelion14th.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleDetailResponse addArticle(String title, String content, String author, String password) {
        Article article = new Article(title, content, author, password);
        articleRepository.save(article);
        return ArticleDetailResponse.from(article);
    }

    @Transactional(readOnly = true)
    public ArticleDetailResponse getOneArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));
        return ArticleDetailResponse.from(article);
    }

    @Transactional //생략가능
    public ArticleDetailResponse updateArticle(Long id, String title, String content, String password) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));
        if(!article.getPassword().equals(password)){
            throw new PermissionDeniedException("해당 게시글에 대한 수정 권한이 없습니다.");
        }
        article.update(title, content);
        articleRepository.save(article);
        return ArticleDetailResponse.from(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleSummaryResponse> getArticles() {
        List<Article> articles = articleRepository.findAll();

        List<ArticleSummaryResponse> articleResponses = articles.stream()
                .map(ArticleSummaryResponse::from)
                .toList();

        return articleResponses;
    }

    @Transactional
    public void deleteArticle(Long id, String password){
        Article article = articleRepository.findById(id)
                        .orElseThrow(() -> new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

        if(!article.getPassword().equals(password)){
            throw new RuntimeException("해당 게시글에 대한 삭제 권한이 없습니다.");
        }
        articleRepository.deleteById(id);
    }
}
