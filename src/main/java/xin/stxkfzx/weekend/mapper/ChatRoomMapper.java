package xin.stxkfzx.weekend.mapper;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import xin.stxkfzx.weekend.entity.ChatRoom;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
@Mapper
public interface ChatRoomMapper {
    /**
     * delete by primary key
     * @param tbId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer tbId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(ChatRoom record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(ChatRoom record);

    /**
     * select by primary key
     * @param tbId primary key
     * @return object by primary key
     */
    ChatRoom selectByPrimaryKey(Integer tbId);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ChatRoom record);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ChatRoom record);

    ChatRoom selectOneByActivateId(@Param("activateId")Integer activateId);


}