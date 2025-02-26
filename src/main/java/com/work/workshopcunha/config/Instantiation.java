package com.work.workshopcunha.config;

import com.work.workshopcunha.domain.Post;
import com.work.workshopcunha.domain.User;
import com.work.workshopcunha.dto.AuthorDTO;
import com.work.workshopcunha.dto.CommentDTO;
import com.work.workshopcunha.repository.PostRepository;
import com.work.workshopcunha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userReposiroty;

    @Autowired
    private PostRepository postReposiroty;

    @Override
    public void run(String... arg0) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userReposiroty.deleteAll();
        postReposiroty.deleteAll();

        User allan = new User(null, "Felipe Allan", "felipera@gmail.com");
        User joao = new User(null, "João Marcelo", "2234521@gmail.com");
        User pedro = new User(null, "Pedro Zada", "zadanodale@gmail.com");

        userReposiroty.saveAll(Arrays.asList(allan, joao, pedro));

        Post post1 = new Post(null, sdf.parse("21/05/2024"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(allan));
        Post post2 = new Post(null, sdf.parse("25/03/2023"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(allan));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(joao));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(pedro));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(joao));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postReposiroty.saveAll(Arrays.asList(post1, post2));

        allan.getPosts().addAll(Arrays.asList(post1, post2));
        userReposiroty.save(allan);
    }

}
