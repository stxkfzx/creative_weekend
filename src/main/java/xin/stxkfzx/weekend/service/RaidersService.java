package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.Raiders;
import xin.stxkfzx.weekend.entity.RaidersContent;
import xin.stxkfzx.weekend.vo.PageVO;


public interface RaidersService {
    Raiders addRaiders(Raiders raiders, RaidersContent raidersContent);

    void updateRaiders(Raiders raiders, RaidersContent raidersContent);

    /**
     * 通过分类id查询
     *
     * @param cid  分类id
     * @param page 查询页数
     * @param rows 每页显示条数
     * @return PageVO<Raiders>
     * @author ViterTian
     * @date 2019-05-07
     */
    PageVO<Raiders> queryByCid(Integer cid, Integer page, Integer rows);
}
