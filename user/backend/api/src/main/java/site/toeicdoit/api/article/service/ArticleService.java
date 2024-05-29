package site.toeicdoit.api.article.service;

import site.toeicdoit.api.article.model.Article;
import site.toeicdoit.api.article.model.ArticleDto;
import site.toeicdoit.api.board.model.BoardModel;
import site.toeicdoit.api.common.command.CommandService;
import site.toeicdoit.api.common.component.MessengerVo;
import site.toeicdoit.api.common.query.QueryService;

import java.util.List;

public interface ArticleService extends CommandService<ArticleDto>, QueryService<ArticleDto> {
    MessengerVo modify(ArticleDto articleDto);
    List<ArticleDto> findUsersByWriter(String Writer);
    default Article dtoToEntity(ArticleDto dto){
        return Article.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .boardModel(BoardModel.builder().id(dto.getBoardId()).build())
                .build();
    }

    default ArticleDto entityToDto(Article article){
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .boardId(article.getBoardModel().getId())
                .regDate(article.getRegDate().toString())
                .modDate(article.getModDate().toString())
                .build();
    }


    List<ArticleDto> findByBoardId(Long id);
}
