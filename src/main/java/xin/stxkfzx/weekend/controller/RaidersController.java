package xin.stxkfzx.weekend.controller;/***
 * ClassName:RaidersController
 * Package:xin.stxkfzx.weekend.online.controller
 * Description:
 * @Date:2019/4/12 0012 17:02
 * @Author:krj@bjpowernode.com
 */

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.stxkfzx.weekend.entity.Raiders;
import xin.stxkfzx.weekend.entity.RaidersContent;
import xin.stxkfzx.weekend.entity.RaidersPar;
import xin.stxkfzx.weekend.entity.ResultBean;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.service.RaidersService;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * ClassName:RaidersController
 */
@RestController
@RequestMapping("/online/raiders")
public class RaidersController {
    public static final Logger logger = getLogger(RaidersController.class);

    private final RaidersService raidersService;

    public RaidersController(RaidersService raidersService) {
        this.raidersService = raidersService;
    }

    /**
     * 新增攻略
     * @param raidersPar	包含俩个对象，一个攻略对象，一个攻略内容对象
     * @return 攻略对象
     * @author krjaydog
     * @date 2019-05-02 12:54
     */
    @RequestMapping("add")
    public ResponseEntity<ResultBean<Raiders>> addRaiders(@RequestBody RaidersPar raidersPar){
        Raiders raiders = raidersPar.getRaiders();
        RaidersContent raidersContent = raidersPar.getRaidersContent();
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultBean<>(StatusEnum.SUCCESS,
                raidersService.addRaiders(raiders, raidersContent)));
    }

    /*@RequestMapping("update")
    public ResponseEntity<ResultBean<Raiders>> updateRaiders*/
}
