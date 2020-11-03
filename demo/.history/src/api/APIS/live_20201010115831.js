import http from '../http'

export const liveApi={
    getCurrentLoginUser(){
        //  console.log('222222');   
         return http.get("/api/currentLoginUser")
    },
    getLogin(){
        //  console.log('222222');   
         return http.get("/login")
    }
    
}