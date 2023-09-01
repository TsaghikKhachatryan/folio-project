package com.folio.blog.folioblogproject.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folio.blog.folioblogproject.controller.PostController;
import com.folio.blog.folioblogproject.dto.PostDto;
import com.folio.blog.folioblogproject.entity.Post;
import com.folio.blog.folioblogproject.entity.Tag;
import com.folio.blog.folioblogproject.service.PostService;
import com.folio.blog.folioblogproject.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PostService postService;

    @MockBean
    TagService tagService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getPosts() throws Exception {
        Set<Tag> tags = new HashSet<>();
        Tag tag1= new Tag();
        tag1.setId(1L);
        tag1.setName("Tag1");
        tags.add(tag1);

        when(tagService.findTagsByIds(any())).thenReturn(tags);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts")
                        .param("tagIds", "1", "2")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deletePost() throws Exception {
        Long postId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/posts/{id}", postId))
                .andExpect(status().isOk());
    }

    @Test
    public void createPost() throws Exception {
        Post post = new Post();
        post.setTags(new HashSet<>());
        post.setTitle("Title");
        post.setContent("Content");
        PostDto postDto = new PostDto(1L, "Title", "Content", new HashSet<>());

        when(postService.createPost(any(Post.class))).thenReturn(postDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/posts")
                        .content(objectMapper.writeValueAsString(post))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title")) // Example assertion
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Content")); // Example assertion
    }


    @Test
    public void updatePostTags() throws Exception {
        Long postId = 1L;
        Set<Tag> tags = new HashSet<>();
        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Tag1");
        tags.add(tag1);

        when(postService.updatePostTags(postId, tags))
                .thenReturn(Optional.of(new PostDto(1L, "Title", "Content", new HashSet<>())));

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/posts/{id}/tags", postId)
                        .content(objectMapper.writeValueAsString(tags))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
