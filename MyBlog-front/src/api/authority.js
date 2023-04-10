import axiosInstance from  "./axiosInstance"

export function getMenu(){
    axiosInstance.post("/getMenu")
}