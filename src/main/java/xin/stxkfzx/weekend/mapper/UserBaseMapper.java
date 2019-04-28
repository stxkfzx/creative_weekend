package xin.stxkfzx.weekend.mapper;

import org.springframework.stereotype.Repository;
import xin.stxkfzx.weekend.entity.UserBase;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@Repository
public interface UserBaseMapper {
    /**
     * 根据主键返回UserBean
     *
     * @param id 主键id
     * @return UserBean
     * @author ViterTian
     * @date 2019-04-13
     */
    UserBase selectByPrimaryKey(Integer id);

    /**
     * 根据用户名返回用户
     * @param username 用户名
     * @return UserBean
     * @author ViterTian
     * @date 2019-04-13
     */
    UserBase selectByNickName(String username);

}