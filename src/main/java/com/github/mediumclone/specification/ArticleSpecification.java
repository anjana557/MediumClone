package com.github.mediumclone.specification;


import com.github.mediumclone.entity.Article;
import com.github.mediumclone.entity.ArticleTag;
import com.github.mediumclone.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor

public class ArticleSpecification {
public static Specification<Article> hasTagName(String tagName){
    return (root, query, criteriaBuilder) -> {
        if (tagName == null || tagName.isBlank()){
            return null;
        }
        Join<Article, ArticleTag>  articleTag = root.join("articleTags",JoinType.LEFT);
        return criteriaBuilder.equal(articleTag.get("tag").get("name"), tagName);
    };
}
public static Specification<Article> hasAuthorName(String authorName){
    return (root, query, criteriaBuilder) -> {
        if (authorName == null || authorName.isBlank()){
            return null;
        }
        Join<Article, User> articleAuthor = root.join("author" , JoinType.INNER);
        return criteriaBuilder.equal(articleAuthor.get("username"),authorName);
    };
}
    public static <ArticleFavorite> Specification<Article> hasFavoriteUsername(String favoriteUsername) {
        return (root, query, criteriaBuilder) -> {
            if (favoriteUsername == null || favoriteUsername.isBlank()) {
                return null;
            }
            Join<ArticleFavorite, User> favoriteUser = root.join("user", JoinType.LEFT);
            return criteriaBuilder.equal(favoriteUser.get("username"), favoriteUsername);
        };
    }

}
