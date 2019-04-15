package xin.stxkfzx.weekend.online.controller;/***
 * ClassName:RaidersController
 * Package:xin.stxkfzx.weekend.online.controller
 * Description:
 * @Date:2019/4/12 0012 17:02
 * @Author:krj@bjpowernode.com
 */

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

   /** 
    * 发布攻略
    * @param raidersPar	 
    * @ return
    * @ author
    * @ date 2019-04-15
    */
   @PostMapping("add/raiders")
   public Integer addRaiders(Raiders raidersPar){
        Integer raidersId = raidersService.addRaiders(raidersPar);
        return raidersId;
   }

   @GetMapping("sel/allRaiders")
   public List<>
}
