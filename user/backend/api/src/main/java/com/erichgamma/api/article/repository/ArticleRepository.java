package com.erichgamma.api.article.repository;

import com.erichgamma.api.article.model.Article;
import com.erichgamma.api.article.model.ArticleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {


    List<Article> findByBoardId(Long id);



    @Query("select a from articles a where a.board.id = :boardId  ORDER BY a.regDate desc " ) //JPQL Default
     List<Article> getArticlesByBoardId(@Param("boardId") Long boardId);



    //native
//    @Query("")
//    List<Article> getReviewArticles();

    // JPSQL Retrun Type Dto
//    String articlesDtoMapping = "new com.example.demo.article.model.ArticleDto(" + "a.id,a.title,a.content,a.writer.id,a.board.id"+")";
//    @Query("SELECT "+ articlesDtoMapping+ "from articles a where a.board.id = :boardId")
//    public  List<ArticleDto> getArticleDTOsBYBoardId(@Param("boardId") Long boardId);



}
