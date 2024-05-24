package com.erichgamma.api.board.service;

import com.erichgamma.api.article.repository.ArticleRepository;
import com.erichgamma.api.board.model.Board;
import com.erichgamma.api.board.model.BoardDto;
import com.erichgamma.api.board.repository.BoardRepository;
import com.erichgamma.api.common.component.MessengerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;


    @Override
    public MessengerVo save(BoardDto boardDto) {
        return MessengerVo.builder()
                .message(Stream.of(boardDto)
                        .peek(i->boardRepository.save(dtoToEntity(i)))
                        .map(i->"SUCCESS")
                        .findAny()
                        .orElseGet(()->"FAILURE")
                )
                .build();
    }

    @Override
    public MessengerVo deleteById(Long id) {
        return MessengerVo.builder()
                .message(
                        Stream.of(id)
                                .filter(i -> boardRepository.existsById(i))
                                .peek(i -> boardRepository.deleteById(i))
                                .map(i -> "SUCCESS")
                                .findAny()
                                .orElseGet(() -> "FAILURE")
                )
                .build();
    }

    @Override
    public MessengerVo modify(BoardDto boardDto) {
        Optional<Board> board = boardRepository.findById(boardDto.getId());
        return MessengerVo.builder().message(
                boardRepository.findById(boardDto.getId()).stream()
                        .peek(i->i.setTitle(boardDto.getTitle()==null?board.get().getTitle():boardDto.getTitle()))
                        .map(i->boardRepository.save(i))
                        .map(i->"SUCCESS")
                        .findAny()
                        .orElseGet(()->"FAIL")
                )
                .build();
    }

    @Override
    public List<BoardDto> findAll() {

        return boardRepository.findAll().stream().map(i->entityToDto(i)).toList();
    }

    @Override
    public Optional<BoardDto> findById(Long id) {
        return boardRepository.findById(id).map(i -> entityToDto(i));
    }

    @Override
    public Long count() {
        return boardRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }





}
