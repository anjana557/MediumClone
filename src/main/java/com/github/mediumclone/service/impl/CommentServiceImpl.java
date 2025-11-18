package com.github.mediumclone.service.impl;

import com.github.mediumclone.dto.RequestDto.CommentRequestDto;
import com.github.mediumclone.dto.request.CommentRequest;
import com.github.mediumclone.dto.response.CommentResponse;
import com.github.mediumclone.entity.Article;
import com.github.mediumclone.entity.ArticleComment;
import com.github.mediumclone.entity.User;
import com.github.mediumclone.exception.ArticleNotFoundException;
import com.github.mediumclone.exception.CommentNotFoundException;
import com.github.mediumclone.exception.UserNotFoundException;
import com.github.mediumclone.mapper.CommentMapper;
import com.github.mediumclone.repository.ArticleCommentRepository;
import com.github.mediumclone.repository.ArticleRepository;
import com.github.mediumclone.repository.UserRepository;
import com.github.mediumclone.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
   private final ArticleRepository articleRepository;
   private final ArticleCommentRepository articleCommentRepository;
   private final UserRepository userRepository;
    @Override
    public CommentResponse createComment(String slug, CommentRequest commentRequest) {
        final Optional<Article> bySlug = articleRepository.findBySlug(slug);
        if (bySlug.isEmpty()){
            throw new ArticleNotFoundException("Article not found");
        }
        final Article article = bySlug.get();

        final Optional<User> userOptional = userRepository.findByUsername("anjana");
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("user not found");
        }
        final User author = userOptional.get();
        final CommentRequestDto commentRequestDto = commentRequest.getComment();
        article.setContent(commentRequestDto.getBody());

        ArticleComment articleComment = new ArticleComment(article, author, commentRequestDto.getBody());

        articleCommentRepository.save(articleComment);
        return CommentMapper.commentMap(articleComment);
    }

    @Override
    public CommentResponse getComment(String slug) {
        final Optional<Article> bySlug = articleRepository.findBySlug(slug);
        if (bySlug.isEmpty()){
            throw new ArticleNotFoundException("Article not found");
        }
        final Article article = bySlug.get();

        final Optional<ArticleComment> byArticle = articleCommentRepository.findByArticle(article);
        if (byArticle.isEmpty()){
            throw new CommentNotFoundException("Comment not found");
        }
        final ArticleComment articleComment = byArticle.get();
        return CommentMapper.commentMap(articleComment);
    }

    @Override
    public void deleteCommentBySlugAndId(String slug, Integer id) {
        final Optional<Article> bySlug = articleRepository.findBySlug(slug);
        if (bySlug.isEmpty()){
            throw new ArticleNotFoundException("Article not found");
        }
        final Article article = bySlug.get();


        articleCommentRepository.findByArticleAndId(article, id)
                .ifPresent(articleCommentRepository::delete);


    }
}
