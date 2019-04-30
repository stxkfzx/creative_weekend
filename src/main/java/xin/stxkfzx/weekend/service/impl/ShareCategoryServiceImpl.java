package xin.stxkfzx.weekend.service.impl;

import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.entity.ShareCategory;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.WeekendException;
import xin.stxkfzx.weekend.mapper.ShareCategoryMapper;
import xin.stxkfzx.weekend.service.ShareCategoryService;

import java.util.Date;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/30
 */
@Service
public class ShareCategoryServiceImpl implements ShareCategoryService {
    private final ShareCategoryMapper shareCategoryMapper;

    public ShareCategoryServiceImpl(ShareCategoryMapper shareCategoryMapper) {
        this.shareCategoryMapper = shareCategoryMapper;
    }

    @Override
    public ShareCategory insertShareCategory(ShareCategory shareCategory) {

        shareCategory.setTbId(null);
        shareCategory.setCreateTime(new Date());
        shareCategory.setUpdateTime(new Date());

        int i = shareCategoryMapper.insertSelective(shareCategory);
        if (i==1){
            return shareCategory;
        }else {
            throw new WeekendException(ExceptionEnum.CATEGORY_CREATION_FAILED);
        }
    }

    @Override
    public Boolean checkCategoryId(Integer categoryId) {
        return shareCategoryMapper.selectByPrimaryKey(categoryId) != null;
    }
}
