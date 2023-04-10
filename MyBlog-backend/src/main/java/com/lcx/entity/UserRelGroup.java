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
@TableName("tb_menu")
public class UserRelGroup {

  private Long userId;
  private Long userGroupId;


}
