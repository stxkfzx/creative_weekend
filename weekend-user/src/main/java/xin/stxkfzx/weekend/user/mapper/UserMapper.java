package xin.stxkfzx.weekend.user.mapper;

import org.springframework.stereotype.Repository;
import xin.stxkfzx.weekend.user.entity.User;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer tbId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer tbId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}