package xin.stxkfzx.weekend.mapper;

import xin.stxkfzx.weekend.entity.ProxyTeam;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
public interface ProxyTeamMapper {
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
    int insert(ProxyTeam record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(ProxyTeam record);

    /**
     * select by primary key
     * @param tbId primary key
     * @return object by primary key
     */
    ProxyTeam selectByPrimaryKey(Integer tbId);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ProxyTeam record);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ProxyTeam record);
}