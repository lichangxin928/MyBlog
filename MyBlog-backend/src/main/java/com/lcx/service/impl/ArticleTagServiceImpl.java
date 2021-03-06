package com.lcx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcx.entity.Article;
import com.lcx.entity.ArticleTag;
import com.lcx.entity.Channel;
import com.lcx.entity.User;
import com.lcx.mapper.ArticleMapper;
import com.lcx.mapper.ArticleTagMapper;
import com.lcx.mapper.ChannelMapper;
import com.lcx.mapper.UserMapper;
import com.lcx.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcx.utils.Maps;
import com.lcx.utils.UserNullUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章标签 服务实现类
 * </p>
 *
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ChannelMapper channelMapper;


    public int create(ArticleTag articleTag){
        return articleTagMapper.create(articleTag);
    }

    public int delete(Integer id){
        return articleTagMapper.delete(Maps.build(id).getMap());
    }

    public int update(ArticleTag articleTag){
        return articleTagMapper.update(Maps.build(articleTag.getId()).beanToMapForUpdate(articleTag));
    }

    public PageInfo<ArticleTag> query(ArticleTag articleTag){
        if (articleTag!=null && articleTag.getPage() != null){
            PageHelper.startPage(articleTag.getPage(),articleTag.getLimit());
        }
        List<ArticleTag> list = articleTagMapper.query(Maps.build().beanToMap(articleTag));
        return new PageInfo<> (list);
    }

    public PageInfo<ArticleTag> getArticleId(ArticleTag articleTag){
        if (articleTag!=null && articleTag.getPage() != null){
            PageHelper.startPage(articleTag.getPage(),8);
        }
        List<ArticleTag> list = articleTagMapper.query(Maps.build(articleTag.getTagId()).beanToMap(articleTag));

        for (int i=0 ; i<list.size();i++){
            Article article = articleMapper.detail(Maps.build(list.get(i).getArticleId()).getMap());
            User user = userMapper.detail(Maps.build(article.getCreateUser()).getMap());
            Channel channel = channelMapper.detail(Maps.build(article.getChannelId()).getMap());
            if (user!=null){
                article.setUser(user);
            }else {
                article.setUser(UserNullUtils.userIsNull());
            }
            article.setChannel(channel);
            list.get(i).setArticle(article);
        }
        return new PageInfo<> (list);
    }

    public ArticleTag detail(Integer id){
        return articleTagMapper.detail(Maps.build(id).getMap());
    }

    public int count(ArticleTag articleTag){
        return articleTagMapper.count(Maps.build().beanToMap(articleTag));
    }
}


