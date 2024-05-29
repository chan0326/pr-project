package site.toeicdoit.api.article;

import site.toeicdoit.api.article.model.ArticleDto;
import site.toeicdoit.api.article.service.ArticleServiceImpl;
import site.toeicdoit.api.common.component.MessengerVo;
import site.toeicdoit.api.user.model.UserDto;
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
@RequestMapping(path = "/api/articles")
@Slf4j
public class ArticleController {
    private final ArticleServiceImpl articleService;

    @SuppressWarnings("static-access")
    @PostMapping("/save")
    public ResponseEntity<MessengerVo> save(@RequestBody ArticleDto dto) throws SQLException {
        log.info("입력받은 정보: {}",dto);
        return ResponseEntity.ok(articleService.save(dto));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<MessengerVo> deleteById(@RequestParam("id") Long id) throws SQLException {
        log.info("입력받은 정보: {}",id);
        return ResponseEntity.ok(articleService.deleteById(id));
    }
    @GetMapping("/list")
    public ResponseEntity<List<ArticleDto>> findAll() throws SQLException {
        return ResponseEntity.ok(articleService.findAll());
    }
    @GetMapping("/detail")
    public ResponseEntity<Optional<ArticleDto>> findById(@RequestParam("id") Long id) throws SQLException {
        return ResponseEntity.ok(articleService.findById(id));
    }
    @GetMapping("/count")
    public ResponseEntity<Long> count() throws SQLException {
        return ResponseEntity.ok(articleService.count());
    }
    @PostMapping("/search")
    public ResponseEntity<List<UserDto>> findUsersByWriter(@RequestBody ArticleDto param) {
//        log.info("입력받은 정보 : {}", param );
//        return ResponseEntity.ok(articleService.findUsersByWriter(param.getWriter()));
        return  null;

    }

    @PutMapping("/modify")
    public ResponseEntity<MessengerVo> modify(@RequestBody ArticleDto param) {
        log.info("입력받은 정보 : {}", param );
        return ResponseEntity.ok(articleService.modify(param));
    }
    @GetMapping("/findByBoardId")
    public ResponseEntity<List<ArticleDto>> findByBoardId(@RequestParam("id") Long id){
        return ResponseEntity.ok(articleService.findByBoardId(id));
    }
}
