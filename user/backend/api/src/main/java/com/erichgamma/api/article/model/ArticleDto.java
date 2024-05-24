package com.erichgamma.api.article.model;

import com.erichgamma.api.user.model.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Log4j2
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private User writer;
    private Long boardId;
    private String regDate;
    private String modDate;


}
