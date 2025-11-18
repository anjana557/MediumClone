package com.github.mediumclone.service.impl;

import com.github.mediumclone.dto.RequestDto.ArticleDto;
import com.github.mediumclone.dto.RequestDto.ArticleUpdateDto;
import com.github.mediumclone.dto.request.ArticleRequest;
import com.github.mediumclone.dto.request.ArticleUpdate;
import com.github.mediumclone.dto.response.ArticleResponse;
import com.github.mediumclone.dto.response.MultipleArticleResponse;
import com.github.mediumclone.entity.Article;
import com.github.mediumclone.entity.ArticleTag;
import com.github.mediumclone.entity.Tag;
import com.github.mediumclone.entity.User;
import com.github.mediumclone.exception.ArticleNotFoundException;
import com.github.mediumclone.mapper.ArticleMapper;
import com.github.mediumclone.repository.ArticleRepository;
import com.github.mediumclone.repository.TagRepository;
import com.github.mediumclone.repository.UserRepository;
import com.github.mediumclone.service.ArticleService;
import com.github.mediumclone.specification.ArticleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        final ArticleDto articleDto = articleRequest.getArticle();
        final Optional<User> userOptional = userRepository.findByUsername("anjana");
        if(userOptional.isEmpty()) {
            throw new ArticleNotFoundException("User not found");
        }
        final User user = userOptional.get();
        Article article = new Article(user,articleDto.getTitle(), articleDto.getDescription(), articleDto.getBody());
        final Article saveArticle = articleRepository.save(article);
        final List<String> tagList = articleDto.getTagList();

        final Set<Tag> tags = tagList.stream().map(Tag::new).collect(Collectors.toSet());

        final List<Tag> savedTags = tagRepository.saveAll(tags);

        for(Tag tag: savedTags) {
            final ArticleTag articleTag = new ArticleTag(saveArticle, tag);
            saveArticle.addTag(articleTag);
        }

        articleRepository.save(saveArticle);
        return ArticleMapper.articleMap(saveArticle);
    }

    @Override
    public ArticleResponse getArticleBySlug(String slug) {
        final Optional<Article> bySlug = articleRepository.findBySlug(slug);
        if (bySlug.isEmpty()){
            throw new ArticleNotFoundException("article not found");
        }
        final Article article = bySlug.get();
        return ArticleMapper.articleMap(article);

    }


    @Override
    public ArticleResponse updateArticleBySlug(String slug, ArticleUpdate articleUpdate) {
        final Optional<Article> bySlug = articleRepository.findBySlug(slug);
        if (bySlug.isEmpty()){
            throw new ArticleNotFoundException("Article not found");
        }
        final Article article = bySlug.get();

        final ArticleUpdateDto articleUpdateDto = articleUpdate.getArticle();

        //final ArticleUpdateDto articleUpdateDto = articleUpdate.getArticle();
        article.setTitle(articleUpdateDto.getTitle());
        article.setDescription(articleUpdateDto.getDescription());
        article.setContent(articleUpdateDto.getBody());
        articleRepository.save(article);
        return ArticleMapper.articleMap(article);
    }

    @Override
    public void deleteArticleBySlug(String slug) {
        final Optional<Article> bySlug = articleRepository.findBySlug(slug);
        if (bySlug.isEmpty()){
            throw new RuntimeException("Article not found");
        }
        final Article article = bySlug.get();
        articleRepository.delete(article);
    }

    @Override
    public MultipleArticleResponse pageResult(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        final Page<Article> articlePage = articleRepository.findAll(pageable);
        final long totalElements = articlePage.getTotalElements();
        final List<Article> content = articlePage.getContent();
        final List<ArticleResponse> list = content.stream()
                .map(ArticleMapper::articleMap)
                .toList();
        final MultipleArticleResponse multipleArticleResponse = new MultipleArticleResponse();
        multipleArticleResponse.setArticlesCount(totalElements);
        multipleArticleResponse.setArticleResponse(list);
        return multipleArticleResponse;


    }

    @Override
    public MultipleArticleResponse getRecentPageResult(String tag, String author, String favorite, Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);

        final Specification<Article> spec = Specification.anyOf(ArticleSpecification.hasAuthorName(author), ArticleSpecification.hasAuthorName(author), ArticleSpecification.hasFavoriteUsername(favorite));
        final Page<Article> articlePage = articleRepository.findAll(spec, pageable);
        final long totalElements1= articlePage.getTotalElements();
        final List<Article> content = articlePage.getContent();
        final List<ArticleResponse> list = content.stream().map(ArticleMapper::articleMap).toList();
        final MultipleArticleResponse multipleArticleResponse1 = new MultipleArticleResponse();
        multipleArticleResponse1.setArticlesCount(totalElements1);
        multipleArticleResponse1.setArticleResponse(list);
        return multipleArticleResponse1;
    }

}
