package com.ohgiraffers.session.user.model.dao;

import com.ohgiraffers.session.user.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Integer regist(SignupDTO newUserDTO);
}
