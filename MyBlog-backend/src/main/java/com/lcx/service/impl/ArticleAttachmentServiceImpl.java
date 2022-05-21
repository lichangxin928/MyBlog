package com.lcx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcx.entity.ArticleAttachment;
import com.lcx.mapper.ArticleAttachmentMapper;
import com.lcx.service.ArticleAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcx.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章附件 服务实现类
 * </p>
 *
 */
@Service
public class ArticleAttachmentServiceImpl extends ServiceImpl<ArticleAttachmentMapper, ArticleAttachment> implements ArticleAttachmentService {

    @Autowired
    private ArticleAttachmentMapper articleAttachmentMapper;


    public int create(ArticleAttachment articleAttachment){
        return articleAttachmentMapper.create(articleAttachment);
    }

    public int delete(Integer id){
        return articleAttachmentMapper.delete(Maps.build(id).getMap());
    }

    public int update(ArticleAttachment articleAttachment){
        System.out.println(articleAttachment);

        System.out.println(Maps.build().beanToMap(articleAttachment));
        return articleAttachmentMapper.update(Maps.build(articleAttachment.getId()).beanToMapForUpdate(articleAttachment));
    }

    public PageInfo<ArticleAttachment> query(ArticleAttachment articleAttachment){
        if (articleAttachment!=null && articleAttachment.getPage() != null){
            PageHelper.startPage(articleAttachment.getPage(),articleAttachment.getLimit());
        }
        List<ArticleAttachment> list = articleAttachmentMapper.query(Maps.build().beanToMap(articleAttachment));
        return new PageInfo<ArticleAttachment> (list);
    }

    public ArticleAttachment detail(Integer id){
        return articleAttachmentMapper.detail(Maps.build(id).getMap());
    }

    public int count(ArticleAttachment articleAttachment){
        return articleAttachmentMapper.count(Maps.build().beanToMap(articleAttachment));
    }

}
