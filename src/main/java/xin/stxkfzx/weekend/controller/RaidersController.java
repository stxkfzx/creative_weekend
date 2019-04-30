package xin.stxkfzx.weekend.controller;/***
 * ClassName:RaidersController
 * Package:xin.stxkfzx.weekend.online.controller
 * Description:
 * @Date:2019/4/12 0012 17:02
 * @Author:krj@bjpowernode.com
 */

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import xin.stxkfzx.weekend.entity.Raiders;
import xin.stxkfzx.weekend.entity.RaidersContent;
import xin.stxkfzx.weekend.service.RaidersService;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * ClassName:RaidersController
 */
@RestController
public class RaidersController {
    public static final Logger logger = getLogger(RaidersController.class);

    private final RaidersService raidersService;

    public RaidersController(RaidersService raidersService) {
        this.raidersService = raidersService;
    }

    /**
     *
     * @param raidersPar    攻略对象
     * @param raidersContent    攻略内容
     * @return
     * @author krjaydog
     * @date 2019-04-30 10:13
     */
    public Raiders addRaiders(Raiders raidersPar, RaidersContent raidersContent){
        Raiders raiders = raidersService.addRaiders(raidersPar);
        return raiders;
    }
}
