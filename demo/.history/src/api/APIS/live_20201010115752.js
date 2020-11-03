import http from '../http'

export const liveApi={
    getCurrentLoginUser(){
        //  console.log('222222');   
         return http.get("/api/currentLoginUser")
    },
    get(){
        //  console.log('222222');   
         return http.get("/api/currentLoginUser")
    }
    
}