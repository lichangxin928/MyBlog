import axios from 'axios'
import store from '../store'
import token from '../store/token'
const axiosInstance=axios.create({
    baseURL: 'http://127.0.0.1:8081/cms/',
    timeout: 100000,
});

// 添加请求拦截器
axiosInstance.interceptors.request.use(config => {

    if (store.state.token){
        config.headers['token'] =store.state.token
    }
    // 在发送请求之前做些什么
    return config;
}, error=>  {
    return Promise.reject(error);
});


// 添加响应拦截器
axiosInstance.interceptors.response.use(response =>{

    let token =response.headers['token']
    console.log(token);
    if (token){
        store.commit('setToken',token)
    }

    //获取后端返回的对象
    const  res = response.data
    if(response.status == 200){
        //数据正常响应
        if(res.status == 200){
            return res;
        }else{
            return Promise.reject(res.message)
        }
    }
}, error=> {
    return Promise.reject(error);
});

export default axiosInstance