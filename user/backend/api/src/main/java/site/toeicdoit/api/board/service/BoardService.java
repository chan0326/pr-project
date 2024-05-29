package site.toeicdoit.api.board.service;

import site.toeicdoit.api.board.model.BoardModel;
import site.toeicdoit.api.board.model.BoardDto;
import site.toeicdoit.api.common.command.CommandService;
import site.toeicdoit.api.common.component.MessengerVo;
import site.toeicdoit.api.common.query.QueryService;

public interface BoardService extends CommandService<BoardDto>, QueryService<BoardDto> {
    MessengerVo modify(BoardDto boardDto);
    default BoardModel dtoToEntity(BoardDto dto){
        return BoardModel.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    default BoardDto entityToDto(BoardModel boardModel){
        return BoardDto.builder()
                .id(boardModel.getId())
                .title(boardModel.getTitle())
                .description(boardModel.getDescription())
                .regDate(boardModel.getRegDate().toString())
                .modDate(boardModel.getModDate().toString())
                .build();
    }


}
