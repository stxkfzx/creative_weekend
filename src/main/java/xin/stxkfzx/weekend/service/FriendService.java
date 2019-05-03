package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.vo.PageVO;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/3
 */
public interface FriendService {

    /**
     * 通过当前用户id和要关注用户id来添加关注信息
     *
     * @param uid 当前用户id
     * @param fid 关注用户id
     * @return 是否关注成功
     * @author ViterTian
     * @date 2019-05-03
     */
    Boolean addAttentionById(Integer uid, Integer fid);

    /**
     * 查看我的粉丝
     *
     * @param uid  当前用户id
     * @param page 查询页数
     * @param rows 每页查询条数
     * @return 我的粉丝列表
     * @author ViterTian
     * @date 2019-05-03
     */
    PageVO queryMyFans(Integer uid, Integer page, Integer rows);

    /**
     * 查看我的关注
     *
     * @param uid  当前用户id
     * @param page 查询页数
     * @param rows 每页查询条数
     * @return 我的关注列表
     * @author ViterTian
     * @date 2019-05-03
     */
    PageVO queryMyAttention(Integer uid, Integer page, Integer rows);

}
