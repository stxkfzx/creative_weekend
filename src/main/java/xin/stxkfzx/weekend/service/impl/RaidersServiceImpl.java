package xin.stxkfzx.weekend.service.impl;/***
 * ClassName:RadersServiceImpl
 * Package:xin.stxkfzx.weekend.service.impl
 * Description:
 * @Date:2019/4/12 0012 17:36
 * @Author:krj@bjpowernode.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.entity.Raiders;
import xin.stxkfzx.weekend.entity.RaidersContent;
import xin.stxkfzx.weekend.mapper.RaidersMapper;
import xin.stxkfzx.weekend.service.RaidersService;

/**
 * ClassName:RadersServiceImpl
 */
@Service
public class RaidersServiceImpl implements RaidersService{

    @Autowired
    RaidersMapper raidersMapper;

    @Override
    public Raiders addRaiders(Raiders raidersPar, RaidersContent raidersContent) {

        return null;
    }
}
