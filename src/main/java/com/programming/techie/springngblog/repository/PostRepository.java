package com.programming.techie.springngblog.repository;

import com.programming.techie.springngblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
