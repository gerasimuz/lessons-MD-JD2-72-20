package com.github.nastyasivko.project_final.dao.repository;

import com.github.nastyasivko.project_final.dao.entity.LoginUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LoginUserRepository extends JpaRepository<LoginUserEntity, Long> {
    Optional<LoginUserEntity> findByLogin(String login);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update LoginUserEntity set password= :password where login = :login")
    void updatePassword(@Param("login") String login, @Param("password") String password);

    Optional<LoginUserEntity> findByUserId(Long userId);
}
