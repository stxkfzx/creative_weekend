package xin.stxkfzx.weekend.mapper;

import org.springframework.stereotype.Repository;
import xin.stxkfzx.weekend.entity.User;

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

    /**
     * 根据用户名返回用户
     *
     * @param username 用户名
     * @return UserBean
     * @author ViterTian
     * @date 2019-04-13
     */
    User selectByNickName(String username);
}