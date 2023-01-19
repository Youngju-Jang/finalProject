package com.hanghae.finalProject.rest.follow.repository;

import com.hanghae.finalProject.rest.follow.model.Follow;
import com.hanghae.finalProject.rest.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
     
     Optional<Follow> findByUserIdAndFollowId(Long id, Long followId);
     List<Follow> findByUser(User user);
}
