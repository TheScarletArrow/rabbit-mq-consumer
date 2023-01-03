package ru.scarlet.rabbit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.scarlet.rabbit.dto.PostInDto;
import ru.scarlet.rabbit.event.Post;
import ru.scarlet.rabbit.event.PostStatus;
import ru.scarlet.rabbit.repository.CommentRepository;
import ru.scarlet.rabbit.repository.PostRepository;
import ru.scarlet.rabbit.service.PostService;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class PostServiceTestJ {

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private PostService postService;


    @Test
    public void getAllTest() {
        Post post = new Post();
        post.setBody("body");
        post.setTitle("title");
        post.setAuthor("author");
        post.setCreatedAt(Instant.now().getEpochSecond());
        List<Post> posts = List.of(post);
        Mockito.when(postRepository.findAll()).thenReturn(posts);
        Assertions.assertEquals(posts, postService.getAll());
    }

    @Test
    public void savePostTest() {
        Post post = new Post();
        post.setBody("body");
        post.setTitle("title");
        post.setAuthor("author");
        post.setCreatedAt(Instant.now().getEpochSecond());
        Mockito.when(postRepository.save(post)).thenReturn(post);
        Assertions.assertEquals(post, postService.savePost(post));
    }

    @Test
    public void savePostByDtoTest() {
        Post post = new Post();
        post.setStatus(PostStatus.PUBLISHED);
        post.setBody("body");
        post.setTitle("title");
        post.setAuthor("author");

        Mockito.when(postRepository.save(post)).thenReturn(post);
        Assertions.assertEquals(post, postService.savePost(post));
        Assertions.assertEquals(post.getStatus(), PostStatus.PUBLISHED);
    }

    @Test
    public void updatePostTest() {
        Post post = new Post();
        post.setBody("body");
        post.setTitle("title");
        post.setAuthor("author");
        post.setCreatedAt(Instant.now().getEpochSecond());
        Mockito.when(postRepository.save(post)).thenReturn(post);
        PostInDto postInDto = new PostInDto();
        Mockito.when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));
        Assertions.assertEquals(post, postService.updatePost(postInDto, post.getId()));
    }

    @Test
    public void getPostByIdTest() {
        Post post = new Post();
        post.setBody("body");
        post.setTitle("title");
        post.setAuthor("author");
        post.setCreatedAt(Instant.now().getEpochSecond());
        post.setId(UUID.randomUUID());
        Mockito.when(postRepository.findById(Objects.requireNonNull(post.getId()))).thenReturn(java.util.Optional.of(post));
        Assertions.assertEquals(post, postService.getPostById(post.getId()));
    }
}
