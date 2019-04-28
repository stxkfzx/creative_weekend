package xin.stxkfzx.weekend.enums;

/**
 * 关联服务标记
 * 主要用于标记服务之间关联调用操作。
 * 例如创建活动需要关联创建聊天室、加入聊天室服务，因此在创建活动时添加ADD标记
 *
 * @author fmy
 * @date 2019-04-26 23:03
 */
public enum LinkTypeEnum {
    /**
     * 添加
     */
    ADD,

    /**
     * 更新
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,
}
