package xin.stxkfzx.weekend.mapper;

import org.apache.ibatis.annotations.Mapper;
import xin.stxkfzx.weekend.entity.ChatMessage;

/**
 * 
 * 
 * @author fmy
 * @date 2019-05-03 17:32 
 */
@Mapper
public interface ChatMessageMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer tbId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(ChatMessage record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(ChatMessage record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    ChatMessage selectByPrimaryKey(Integer tbId);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ChatMessage record);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ChatMessage record);
}