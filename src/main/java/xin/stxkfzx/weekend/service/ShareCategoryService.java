package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.ShareCategory;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/30
 */
public interface ShareCategoryService {
    /**
     * 新增分享视频类型
     *
     * @param shareCategory shareCategory
     * @return ShareCategory
     * @author ViterTian
     * @date 2019-04-30
     */
    ShareCategory insertShareCategory(ShareCategory shareCategory);

    /**
     * 校验分类id是否存在
     *
     * @param categoryId 分类id
     * @return Boolean true-存在（可用） false-不存在（不可用）
     * @author ViterTian
     * @date 2019-04-30
     */
    Boolean checkCategoryId(Integer categoryId);
}
