package com.airbnb.hhairbnbclone.repository;

import com.airbnb.hhairbnbclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByKakaoId(Long kakaoId);
    boolean existsByKakaoId(Long kakaoId);
}
