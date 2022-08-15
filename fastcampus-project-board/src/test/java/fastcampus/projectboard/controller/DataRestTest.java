package fastcampus.projectboard.controller;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.*;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("API Test")
@Transactional//기본 동작이 Rollback으로 처리됨
public class DataRestTest {
    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
    @DisplayName("게시글 api 리스트 조회")
    @Test
    void givenothing_whenRequestingArticles_thenReturnsArticles() throws Exception {
        mvc.perform(get("/api/articles")).andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json"))).
                andDo(print());// 현재 상태가 정상인지 확인 그 이후 내용이 JSON인지 확인 그리고 내용 출력
    }
    @DisplayName("게시글 api 단일 조회")
    @Test
    void givenothing_whenRequestingArticle_thenReturnsArticles() throws Exception {
        mvc.perform(get("/api/articles/1")).andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json"))).
                andDo(print());// 현재 상태가 정상인지 확인 그 이후 내용이 JSON인지 확인 그리고 내용 출력
    }
    @DisplayName("게시글 댓글 api 조회")
    @Test
    void givenothing_whenRequestingArticleComment_thenReturnsArticles() throws Exception {
        mvc.perform(get("/api/article/1/articleComments")).andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json"))).
                andDo(print());// 현재 상태가 정상인지 확인 그 이후 내용이 JSON인지 확인 그리고 내용 출력
    }
    @DisplayName("댓글 리스트 api 조회")
    @Test
    void givenothing_whenRequestingArticleComments_thenReturnsArticles() throws Exception {
        mvc.perform(get("/api/articleComments")).andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json"))).
                andDo(print());// 현재 상태가 정상인지 확인 그 이후 내용이 JSON인지 확인 그리고 내용 출력
    }
}
