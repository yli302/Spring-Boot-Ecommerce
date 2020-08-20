package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.UserLoginInfoModel;

public interface UserLoginInfoRepository extends JpaRepository<UserLoginInfoModel, String>{

}
