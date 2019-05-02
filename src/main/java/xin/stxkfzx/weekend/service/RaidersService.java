package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.Raiders;
import xin.stxkfzx.weekend.entity.RaidersContent;


public interface RaidersService {
    Raiders addRaiders(Raiders raiders, RaidersContent raidersContent);

    void updateRaiders(Raiders raiders, RaidersContent raidersContent);
}
