package xin.stxkfzx.weekend.service.impl;/***
 * ClassName:RadersServiceImpl
 * Package:xin.stxkfzx.weekend.service.impl
 * Description:
 * @Date:2019/4/12 0012 17:36
 * @Author:krj@bjpowernode.com
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.entity.Raiders;
import xin.stxkfzx.weekend.entity.RaidersContent;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.exception.WeekendException;
import xin.stxkfzx.weekend.mapper.RaidersContentMapper;
import xin.stxkfzx.weekend.mapper.RaidersMapper;
import xin.stxkfzx.weekend.service.RaidersService;
import xin.stxkfzx.weekend.service.ShareCategoryService;
import xin.stxkfzx.weekend.service.UserService;

import java.util.Date;

/**
 * ClassName:RadersServiceImpl
 */
@Service
public class RaidersServiceImpl implements RaidersService {

    private Logger logger = LoggerFactory.getLogger(VideoShareServiceImpl.class);

    @Autowired
    RaidersMapper raidersMapper;
    @Autowired
    ShareCategoryService shareCategoryService;
    @Autowired
    UserService userService;
    @Autowired
    RaidersContentMapper raidersContentMapper;

    @Override
    public Raiders addRaiders(Raiders raiders, RaidersContent raidersContent) {
        // 校验用户id和分类id及是否合理
        boolean flag = shareCategoryService.checkCategoryId(raiders.getCategoryId()) && userService.checkUserId(raiders.getUserId());
        if (!flag) {
            logger.error("此用户：{}提交数据不合理不存在，有可能是用户id或分类id不存在", raiders.getUserId());
            throw new WeekendException(ExceptionEnum.CATEGORY_ID_NOT_EXIST);
        }
        //插入活动内容，返回id
        int raidersContentId = raidersContentMapper.insertSelective(raidersContent);
        //设置其他参数
        raiders.setCreateTime(new Date());
        raiders.setUpdateTime(new Date());
        raiders.setStatus(StatusEnum.NORMAL);
        raiders.setContentId(raidersContentId);
        //插入攻略
        final int i = raidersMapper.insertSelective(raiders);
        if (i == 1) {
            logger.info("新增攻略{}成功.", raiders);
            return raiders;
        } else {
            logger.error("新增攻略{}失败.", raiders);
            throw new WeekendException(ExceptionEnum.ADD_Raiders_FAILED);
        }
    }

    @Override
    public void updateRaiders(Raiders raiders, RaidersContent raidersContent) {
        final int i = raidersMapper.updateByPrimaryKeySelective(raiders);
        final int i1 = raidersContentMapper.updateByPrimaryKeySelective(raidersContent);

        if(i == 1 && i1 ==1){
            logger.info("更新攻略{}成功.", raiders);
        }else{
            logger.error("更新攻略{}失败.", raiders);
            throw new WeekendException(ExceptionEnum.UPDATE_Raiders_FAILED);
        }
    }
}
