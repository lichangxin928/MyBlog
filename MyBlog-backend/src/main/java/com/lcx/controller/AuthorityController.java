package com.lcx.controller;

import com.lcx.entity.Menu;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lichangxin2
 * @date 2023/3/31 21:39
 * @Description 用户权限 controller
  */

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @PostMapping("/getMenu")
    List<Menu> getMenu(){
        return new ArrayList<>();
    }
}
