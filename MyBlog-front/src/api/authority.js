import axiosInstance from  "./axiosInstance"
import fr from "element-ui/src/locale/lang/fr";

export function getMenu(){
    return axiosInstance.post("/authority/getMenu")
}

export function getUserGroup(){
    return axiosInstance.post("/authority/userGroup")
}

export function getUserByGroupId(id){
    return axiosInstance.get("/authority/userByGroupId/" + id)
}

export function deleteUserGroup(groupId,userId){
    return axiosInstance.get("/authority/group/delete/"+groupId+"/"+userId+"")
}

export function getUnUserList(groupId){
    return axiosInstance.get("/authority/group/userList/" + groupId)
}

export function addGroupRelUserList(groupRelUserReq){
    return axiosInstance.post("/authority/group/addUser",groupRelUserReq)
}

export function getMenuByGroupId(groupId){
    return axiosInstance.get("/authority/group/getMenu/" + groupId)
}

export function deleteGroupRelMenu(groupId,userId){
    return axiosInstance.get("/authority/group/delete/menu/"+groupId+"/"+userId+"")
}

export function  getUnMenuList(groupId){
    return axiosInstance.get("/authority/group/menuList/" + groupId)
}

export function addGroupRelMenu(query){
    return axiosInstance.post("/authority/group/add/menu",query)
}

export function groupList(){
    return axiosInstance.post("/authority/group")
}

export function deleteGroup(groupId){
    return axiosInstance.get("/authority/group/delete/" + groupId )
}

export function saveOrUpdate(form){
    return axiosInstance.post("/authority/group/update",form)
}

export function saveGroup(form){
    return axiosInstance.post("/authority/group/save",form)
}