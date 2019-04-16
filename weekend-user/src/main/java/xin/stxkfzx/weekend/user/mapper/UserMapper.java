package xin.stxkfzx.weekend.user.mapper;

import org.springframework.stereotype.Repository;
import xin.stxkfzx.weekend.user.entity.User;
/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer tbId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer tbId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int insertById(Integer userId, User user);
}