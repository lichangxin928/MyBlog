package com.lcx.controller;


import com.github.pagehelper.PageInfo;
import com.lcx.entity.ArticleAttachment;
import com.lcx.service.impl.ArticleAttachmentServiceImpl;
import com.lcx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 文章附件 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/article-attachment")
public class ArticleAttachmentController {
    @Autowired
    private ArticleAttachmentServiceImpl articleAttachmentService;

    @PostMapping("/create")
    public Result create(@RequestBody ArticleAttachment articleAttachmen){
        articleAttachmentService.create(articleAttachmen);
        return Result.ok(articleAttachmen);
    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        articleAttachmentService.delete(id);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody  ArticleAttachment articleAttachment){

        System.out.println("我是update");
        System.out.println("articleAttachment====>"+articleAttachment);
        articleAttachmentService.update(articleAttachment);
        return Result.ok(articleAttachment);
    }
    @PostMapping("/query")
    public Map query(@RequestBody ArticleAttachment articleAttachmen){
        PageInfo<ArticleAttachment> pageInfo = articleAttachmentService.query(articleAttachmen);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id){
        ArticleAttachment detail = articleAttachmentService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody ArticleAttachment articleAttachment){
        int count = articleAttachmentService.count(articleAttachment);
        return Result.ok(count);
    }
}

