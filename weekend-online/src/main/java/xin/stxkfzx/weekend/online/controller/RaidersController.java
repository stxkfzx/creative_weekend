package xin.stxkfzx.weekend.online.controller;/***
 * ClassName:RaidersController
 * Package:xin.stxkfzx.weekend.online.controller
 * Description:
 * @Date:2019/4/12 0012 17:02
 * @Author:krj@bjpowernode.com
 */

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import xin.stxkfzx.weekend.online.entity.Raiders;
import xin.stxkfzx.weekend.online.service.RaidersService;

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

    public Raiders addRaiders(Raiders raidersPar){
        Raiders raiders = raidersService.addRaiders(raidersPar);
        return raiders;
    }
}
