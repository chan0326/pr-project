package site.toeicdoit.api.board;

import site.toeicdoit.api.board.model.BoardDto;
import site.toeicdoit.api.board.service.BoardServiceImpl;
import site.toeicdoit.api.common.component.MessengerVo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RestController  //controller + ResponseBody
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(path = "/api/boards")
@Slf4j
public class BoardController {
    private final BoardServiceImpl boardService;

    @SuppressWarnings("static-access")
    @PostMapping("")
    public ResponseEntity<MessengerVo> save(BoardDto dto) throws SQLException {
        log.info("입력받은 정보: {}",dto);
        return ResponseEntity.ok(boardService.save(dto));
    }
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> findAll() throws SQLException {
        return ResponseEntity.ok(boardService.findAll());
    }
    @GetMapping("/count")
    public ResponseEntity<Long> count() throws SQLException {
        return ResponseEntity.ok(boardService.count());
    }
    @GetMapping("/detail")
    public ResponseEntity<Optional<BoardDto>> findById(@RequestParam Long id) throws SQLException {
        return ResponseEntity.ok((boardService.findById(id)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<MessengerVo> deleteById(@RequestParam Long id) throws SQLException {
        log.info("입력받은 정보: {}",id);
        return ResponseEntity.ok(boardService.deleteById(id));
    }

    //보드컨트롤러

}
