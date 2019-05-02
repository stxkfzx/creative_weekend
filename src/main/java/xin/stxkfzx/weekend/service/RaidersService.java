package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.Raiders;
import xin.stxkfzx.weekend.entity.RaidersContent;

/**
 * ClassName:RaidersService
 * Package:xin.stxkfzx.weekend.service
 * Description:
 *
 * @date: 2019/4/12 0012 17:26
 * @Author:krj@bjpowernode.com
 */

public interface RaidersService {
    public Raiders addRaiders(Raiders raiders, RaidersContent raidersContent);

    public void updateRaiders(Raiders raiders, RaidersContent raidersContent);
}
