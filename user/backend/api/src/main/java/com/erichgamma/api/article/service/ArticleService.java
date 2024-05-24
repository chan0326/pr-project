package com.erichgamma.api.article.service;

import com.erichgamma.api.article.model.Article;
import com.erichgamma.api.article.model.ArticleDto;
import com.erichgamma.api.board.model.Board;
import com.erichgamma.api.common.command.CommandService;
import com.erichgamma.api.common.component.MessengerVo;
import com.erichgamma.api.common.query.QueryService;
import com.erichgamma.api.user.model.User;
import com.erichgamma.api.user.model.UserDto;

import java.util.*;

public interface ArticleService extends CommandService<ArticleDto>, QueryService<ArticleDto> {
    MessengerVo modify(ArticleDto articleDto);
    List<ArticleDto> findUsersByWriter(String Writer);
    default Article dtoToEntity(ArticleDto dto){
        return Article.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .board(Board.builder().id(dto.getBoardId()).build())
                .build();
    }

    default ArticleDto entityToDto(Article article){
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .boardId(article.getBoard().getId())
                .regDate(article.getRegDate().toString())
                .modDate(article.getModDate().toString())
                .build();
    }


    List<ArticleDto> findByBoardId(Long id);
}
