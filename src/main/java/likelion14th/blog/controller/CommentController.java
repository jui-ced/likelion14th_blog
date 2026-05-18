package likelion14th.blog.controller;

import likelion14th.blog.dto.request.CommentRequest;
import likelion14th.blog.dto.response.ApiResponse;
import likelion14th.blog.dto.response.CommentResponse;
import likelion14th.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{articleId}/comments")
    public ResponseEntity<ApiResponse<CommentResponse>> addComment(@RequestBody CommentRequest commentRequest, @PathVariable Long articleId) {
        CommentResponse commentResponse = commentService.addComment(commentRequest.getContent(),commentRequest.getAuthor(), articleId);

        return ResponseEntity.ok(ApiResponse.success(201, "댓글 생성에 성공하였습니다.", commentResponse));
    }
}
