package xin.stxkfzx.weekend.convert;

import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import xin.stxkfzx.weekend.vo.PageVO;

import java.util.List;

/**
 * @author fmy
 * @date 2019-04-30 22:11
 */
@Mapper(componentModel = "spring")
public interface PageConvert {

    @Mappings({
            @Mapping(target = "list", source = "list"),
            @Mapping(target = "firstPage", source = "page.isFirstPage"),
            @Mapping(target = "lastPage", source = "page.isLastPage")
    })
    PageVO fromPageInfo(PageInfo page, List<?> list);

}
