package xin.stxkfzx.weekend.controller;

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

import javax.validation.constraints.Min;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultBean<>(StatusEnum.SUCCESS,
                raidersService.addRaiders(getRaidersParOfRaiders(raidersPar), getRaidersParOfRaidersContent(raidersPar))));
    }

    /**
     * 修改攻略
     * @param raidersPar	 包含俩个对象，一个攻略对象，一个攻略内容对象
     * @return 没啥
     * @author krjaydog
     * @date 2019-05-02 17:14
     */
    @RequestMapping("update")
    public ResponseEntity updateRaiders(@RequestBody RaidersPar raidersPar){
        raidersService.updateRaiders(getRaidersParOfRaiders(raidersPar), getRaidersParOfRaidersContent(raidersPar));
        return ResponseEntity.ok(new ResultBean<>(StatusEnum.SUCCESS));
    }

    /*@RequestMapping("select")
    public ResponseEntity<ResultBean<List<RaidersPar>>> selectRaiders(@Min(0) Integer categoryId){

    }*/


    /**
     * 获取raidersPar参数中的raiders对象
     * @param raidersPar	 包含俩个对象，一个攻略对象，一个攻略内容对象
     * @return raiders对象
     * @author krjaydog
     * @date 2019-05-02 17:14
     */
    private Raiders getRaidersParOfRaiders(RaidersPar raidersPar){
        return raidersPar.getRaiders();
    }

    /**
     * 获取raidersPar参数中的raidersContent对象
     * @param raidersPar	 包含俩个对象，一个攻略对象，一个攻略内容对象
     * @return raidersContent对象
     * @author krjaydog
     * @date 2019-05-02 17:14
     */
    private RaidersContent getRaidersParOfRaidersContent(RaidersPar raidersPar){
        return raidersPar.getRaidersContent();
    }
}
