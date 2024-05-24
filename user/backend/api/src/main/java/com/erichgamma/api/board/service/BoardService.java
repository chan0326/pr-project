package com.erichgamma.api.board.service;

import com.erichgamma.api.board.model.Board;
import com.erichgamma.api.board.model.BoardDto;
import com.erichgamma.api.common.command.CommandService;
import com.erichgamma.api.common.component.MessengerVo;
import com.erichgamma.api.common.query.QueryService;
import com.erichgamma.api.user.model.UserDto;

import java.util.*;

public interface BoardService extends CommandService<BoardDto>, QueryService<BoardDto> {
    MessengerVo modify(BoardDto boardDto);
    default Board dtoToEntity(BoardDto dto){
        return Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    default BoardDto entityToDto(Board board){
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .regDate(board.getRegDate().toString())
                .modDate(board.getModDate().toString())
                .build();
    }


}
