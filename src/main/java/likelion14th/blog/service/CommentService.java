package likelion14th.blog.service;

import jakarta.persistence.EntityNotFoundException;
import likelion14th.blog.domain.Article;
import likelion14th.blog.domain.Comment;
import likelion14th.blog.dto.response.CommentResponse;
import likelion14th.blog.repository.ArticleRepository;
import likelion14th.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentResponse addComment(String content, String author, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));

       Comment comment = new Comment(author, content, article);
       commentRepository.save(comment);

       CommentResponse response = CommentResponse.of(article.getId(), comment);
       return response;
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long articleId){
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        List<CommentResponse> responses = comments.stream()
                .map(comment -> CommentResponse.of(articleId, comment))
                .toList();
        return responses;
    }
}
