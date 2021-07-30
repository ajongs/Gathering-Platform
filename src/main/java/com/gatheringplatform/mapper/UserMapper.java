package com.gatheringplatform.mapper;

import com.gatheringplatform.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    void signUp(User user);
}
