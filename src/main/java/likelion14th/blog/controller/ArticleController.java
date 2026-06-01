package likelion14th.blog.controller;

import jakarta.validation.Valid;
import likelion14th.blog.dto.request.ArticleRequest;
import likelion14th.blog.dto.request.DeleteArticleRequest;
import likelion14th.blog.dto.request.UpdateArticleRequest;
import likelion14th.blog.dto.response.ApiResponse;
import likelion14th.blog.dto.response.ArticleDetailResponse;
import likelion14th.blog.dto.response.ArticleSummaryResponse;
import likelion14th.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ApiResponse<ArticleDetailResponse>> addArticle(@Valid @RequestBody ArticleRequest request) {
        ArticleDetailResponse articleDetailResponse = articleService.addArticle(request.getTitle(), request.getContent(), request.getAuthor(), request.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(201, "게시글 생성에 성공하였습니다.", articleDetailResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ArticleDetailResponse>> getOneArticle(@PathVariable Long id) {
        ArticleDetailResponse articleDetailResponse = articleService.getOneArticle(id);

        return ResponseEntity.ok(ApiResponse.success(200,"게시글 조회에 성공하였습니다.", articleDetailResponse));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ArticleDetailResponse>> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        ArticleDetailResponse articleDetailResponse = articleService.updateArticle(id, request.getTitle(), request.getContent(), request.getPassword());

        return ResponseEntity.ok(ApiResponse.success(200, "게시글을 업데이트 하였습니다.", articleDetailResponse));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<ArticleSummaryResponse>>> getArticles() {
        List<ArticleSummaryResponse> articleDetailResponses = articleService.getArticles();

        return ResponseEntity.ok(ApiResponse.success(200, "게시글 전체 조회에 성공하였습니다.", articleDetailResponses));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteArticle(
            @PathVariable Long id,
            @RequestBody DeleteArticleRequest deleteArticleRequest) {
        articleService.deleteArticle(id, deleteArticleRequest.getPassword());

        return ResponseEntity.ok(ApiResponse.success(204, "게시글을 삭제하는데 성공하였습니다."));
    }
}
