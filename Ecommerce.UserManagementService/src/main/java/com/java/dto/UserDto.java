package com.java.dto;

import com.java.model.UserLoginInfoModel;
import com.java.model.UserProfileModel;

import lombok.Data;

@Data
public class UserDto {
	private UserProfileModel profileModel;
	private UserLoginInfoModel loginInfoModel;
}
