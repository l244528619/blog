import http from '../http'

export const liveApi={
    //判断是否登录
    getCurrentLoginUser(){
        //  console.log('222222');   
         return http.get("/api/currentLoginUser")
    },
    //登录
    getLogin({userName="",password=""}){
        // login?userName=admin&password=123456
         return http.get("/login",{
            userName:userName,
            password:password

         }
         ) 
    },
    //退出
    getLogout(){
        return http.get("/logout")
    },
    //获取验证码(返回的是图片)
    getVerification() {
        return http.get("/api/captcha/getCaptcha")

    },
    getSaveUser() {
        return http.get("/api/user/saveUser",{
            userName:userName,
        })
    }
    
}


/*
获取所有用户：/api/user/getAllUser

保存用户信息：/api/user/saveUser?userName=ying&password=123456&mobile=18819941228&captcha=0000

更新用户密码：/api/user/updateUserPassword?userName=ying&password=12345678

更新用户手机号：/api/user/updateUserMobile?userName=ying&mobile=18819951228

根据用户名获取用户信息：/api/user/getUserByName?userName=ying

删除用户：/api/user/deleteUser?userName=ying

获取验证码(返回的是图片)：/api/captcha/getCaptcha  

// 缺少一个 查询手机号是否存在
*/