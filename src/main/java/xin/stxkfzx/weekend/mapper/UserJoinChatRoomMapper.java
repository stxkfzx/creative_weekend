package xin.stxkfzx.weekend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import xin.stxkfzx.weekend.entity.UserJoinChatRoom;
import xin.stxkfzx.weekend.enums.StatusEnum;

import java.util.List;

/**
 * @author fmy
 * @date 2019-04-27 0:43
 */
@Mapper
public interface UserJoinChatRoomMapper {
    /**
     * delete by primary key
     *
     * @param tbId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer tbId);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(UserJoinChatRoom record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(UserJoinChatRoom record);

    /**
     * select by primary key
     *
     * @param tbId primary key
     * @return object by primary key
     */
    UserJoinChatRoom selectByPrimaryKey(Integer tbId);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(UserJoinChatRoom record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(UserJoinChatRoom record);

    UserJoinChatRoom selectOneByUserIdAndRoomId(@Param("userId") Integer userId, @Param("roomId") Integer roomId);

    List<UserJoinChatRoom> selectByUserIdAndRoomIdAndStatus(@Param("userId") Integer userId, @Param("roomId") Integer roomId, @Param("status") Short status);

    int updateListByTbId(List<UserJoinChatRoom> recordList);

    int updateByRoomId(@Param("updated")UserJoinChatRoom updated,@Param("roomId")Integer roomId);

	int updateByRoomIdAndUserId(@Param("updated")UserJoinChatRoom updated,@Param("roomId")Integer roomId,@Param("userId")Integer userId);

}