package com.app.boot.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.entities.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	Optional<UserInfo> findByName(String username);

}
