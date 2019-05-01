package xin.stxkfzx.weekend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.stxkfzx.weekend.entity.ResultBean;
import xin.stxkfzx.weekend.entity.ShareCategory;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.service.ShareCategoryService;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/29
 */
@RestController
@RequestMapping("/online/share/category")
public class ShareCategoryController {
    private final ShareCategoryService shareCategoryService;

    public ShareCategoryController(ShareCategoryService shareCategoryService) {
        this.shareCategoryService = shareCategoryService;
    }

    /**
     * 新增分享类别
     *
     * @param shareCategory shareCategory
     * @return ResponseEntity<ResultBean < ShareCategory>>
     * @author ViterTian
     * @date 2019-04-30
     */
    @PostMapping("/insert")
    public ResponseEntity<ResultBean<ShareCategory>> insert(@RequestBody ShareCategory shareCategory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultBean<>(StatusEnum.SUCCESS, shareCategoryService.insertShareCategory(shareCategory)));
    }
}
