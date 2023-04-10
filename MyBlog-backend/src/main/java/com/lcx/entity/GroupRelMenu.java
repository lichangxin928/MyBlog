package com.lcx.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichangxin2
 * @date 2023/3/31 21:39
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_group_rel_menu")
public class GroupRelMenu {

  private Long userGroupId;
  private Long menuId;

}
