import Vue from 'vue'

import  VueRouter from 'vue-router'
import Login from '../views/admin/Login'
import Index from "../views/admin/Index";
import User from "../views/admin/user/Index";
import Tag from "../views/admin/tag/Index";
import Channel from "../views/admin/channel/Index";
import FriendLink from "../views/admin/friendlink/Index";
import FriendLinkAdd from "../views/admin/friendlink/Add";
import FriendLinkEdit from "../views/admin/friendlink/Edit";
import Article from "../views/admin/article/Index";
import Comment from "../views/admin/comment/Index";
import Check from "../views/admin/comment/Checkl";
import Info from "../views/admin/user/Info";
import  Channel_add from '../views/admin/channel/Add'
import  Channel_edit from '../views/admin/channel/Edit'
import  ArticleAdd from '../views/admin/article/Add'
import  ArticleEdit from '../views/admin/article/Edit'
import Auth from '../views/admin/authority/index'

import FIndex from  '../views/front/Index'
import List from  '../views/front/List'
import Detail from  '../views/front/Detail'
import ChannelDetail from  '../views/front/ChannelDetail'
import TagList from  '../views/front/TagList'

import token from '../store/token'
import AddGroupUser from "@/views/admin/authority/AddGroupUser";
import UserGroupRelMenu from "@/views/admin/authority/UserGroupRelMenu";
import AddGroupMenu from "@/views/admin/authority/AddGroupMenu";
import Register from "@/views/admin/Register";
import Group from "@/views/admin/authority/Group";

Vue.use(VueRouter)

const router=new VueRouter({
    // mode: 'history',
    routes:[
        {
            path: '/',
            name: 'fIndex',
            component: FIndex,
            meta:{
                requireLogin: false
            }
        },
        {
            path: '/content',
            name: 'fIndex',
            component: FIndex,
            meta:{
                requireLogin: false
            }
        },
        // Header 栏中的信息
        {
            path: '/list/:id',
            name: 'list_id',
            component: List,
            meta:{
                requireLogin: false
            }
        },
        // 各个文章信息
        {
            path: '/detail/:id',
            name: 'detail',
            component: Detail,
            meta:{
                requireLogin: false
            }
        },
        {
            path: '/channel_detail/:id',
            name: 'channel_detail',
            component: ChannelDetail,
            meta:{
                requireLogin: false
            }
        },

        {
            path: '/tag_list/:id',
            name: 'channel_detail',
            component: TagList,
            meta:{
                requireLogin: false
            }
        },
        {
          path: '/login',
          name: 'login',
          component:   Login
        },
        {
            path:'/register',
            name:'register',
            component:Register
        },
        {
            path: '/index',
            name: 'index',
            component:   Index,
            children:[
                {
                    path: '/info',
                    name :'info',
                    component: Info
                },
                {
                    path:"/group",
                    name:"group",
                    component: Group
                }
                ,
                {
                    path: '/user',
                    name :'User',
                    component: User
                },
                {
                    path: '/channel',
                    name :'channel',
                    component: Channel
                },
                {
                    path: '/channel_add',
                    name :'channel_add',
                    component: Channel_add
                },
                {
                    path: '/channel_edit',
                    name :'channel_edit',
                    component: Channel_edit
                },
                {
                    path: '/tag',
                    name :'tag',
                    component: Tag
                },
                {
                    path: '/friend',
                    name :'friend',
                    component: FriendLink
                },
                {
                    path: '/friend_add',
                    name :'friend_add',
                    component: FriendLinkAdd
                },
                {
                    path: '/friend_edit',
                    name :'friend_edit',
                    component: FriendLinkEdit
                },
                {
                    path:'/userGroup_add',
                    name:'userGroup_add',
                    component:AddGroupUser
                },
                {
                    path: '/article',
                    name :'article',
                    component: Article
                },
                {
                    path: '/article_add',
                    name :'article_add',
                    component: ArticleAdd
                },
                {
                    path: '/article_edit',
                    name :'article_edit',
                    component: ArticleEdit
                },
                {
                    path: '/comment',
                    name :'comment',
                    component: Comment
                },
                {
                    path: '/comment_check',
                    name :'comment_check',
                    component: Check
                },
                {
                    path:'/auth',
                    name:'authority',
                    component:Auth
                },
                {
                    path:"/group_menu",
                    name:"groupMenu",
                    component:UserGroupRelMenu
                },
                {
                    path:"/userGroupMenu_add",
                    name:"AddGroupMenu",
                    component:AddGroupMenu
                }
            ]
        }
    ]
})


router.beforeEach((to, from, next) => {

    if (!to.matched.some(r=>r.meta.requireLogin)) {
        next()
    }else {
        if (token.get()){
            next()
        }else {
            if (to.path === '/login'){
                next()
            } else {
                next('/login')
            }
        }
    }


})

export default  router