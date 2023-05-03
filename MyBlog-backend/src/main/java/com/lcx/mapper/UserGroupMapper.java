package com.lcx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcx.entity.Menu;
import com.lcx.entity.UserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lichangxin2
 * @date 2023/3/31 21:47
 * @Description
 */
@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroup> {

    @Select("SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\ttb_menu \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT\n" +
            "\t\tmenu_id \n" +
            "\tFROM\n" +
            "\t\ttb_group_rel_menu \n" +
            "\tWHERE\n" +
            "\tuser_group_id IN ( SELECT user_group_id FROM tb_user_rel_group WHERE user_id = #{userId} ) \n" +
            "\t) ")
    List<Menu> findMenuByUserId(@Param("userId") Integer userId);
}
