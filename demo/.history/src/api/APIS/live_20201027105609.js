import http from '../http'

export const liveApi={
    getCurrentLoginUser({TOKEN=""}){
        //  console.log('222222');   
         return http.get("/api/currentLoginUser",{
            TOKEN:TOKEN
         })
    },
    getLogin({userName="",password=""}){
        // login?userName=admin&password=123456
         return http.get("/login",{
            userName:userName,
            password:password

         }
         ) 
    }
    
}