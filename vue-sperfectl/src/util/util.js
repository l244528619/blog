const axios = require('axios')
// String.prototype.endsWith = function(str) {
//     if (str == null || str == "" || this.length == 0 || str.length > this.length)
//         return false;
//     if (this.substring(this.length - str.length) == str)
//         return true;
//     else
//         return false;
//     return true;
// }
/*http://47.98.226.42/currentLoginUser 这个接口是获取当前登录用户的
http://47.98.226.42/login?userName=admin&password=123456 这个是登录接口
http://47.98.226.42/logout 这个接口是退出登录 */

var utils = {
    baseurl: 'http://47.98.226.42/',
    get: function(url, param, callback) {
        axios.get(this.baseurl + url, { params: param }).then(function(res) {
            callback(res.data)
        })
    },
    post: function(url, param, callback) {
        var params = new URLSearchParams()

        for (var i in param) {
            if (typeof param[i] !== 'object') {
                params.append(i, param[i])
            } else {
                for (var j in param[i]) {
                    if (j === 'id') {
                        params.append(i + '.' + j, param[i][j])
                        break
                    }
                }
            }
        }
        axios.post(this.baseurl + url, params).then(function(res) {
            callback(res.data)
        })
    },
//     getTime2Time: function($time1, $time2) {
//         var time1 = arguments[0]
//         var time2 = arguments[1]
//         time1 = Date.parse(time1) / 1000
//         time2 = Date.parse(time2) / 1000
//         var time_ = time1 - time2
//         return (time_ / (3600 * 24))
//     },
//     formatTime: function(date) {
//         const year = date.getFullYear()
//         const month = (date.getMonth() + 1).toString().padStart(2, '0')
//         const day = date.getDate().toString().padStart(2, '0')
//         const hour = date.getHours().toString().padStart(2, '0')
//         const minute = date.getMinutes().toString().padStart(2, '0')
//         return [year, month, day].map(this.formatNumber).join('-') + ' ' + [hour, minute].map(this.formatNumber).join(':')
//     },
//     formatShortTime: function(date) {
//         const year = date.getFullYear()
//         const month = (date.getMonth() + 1).toString().padStart(2, '0')
//         const day = date.getDate().toString().padStart(2, '0')

//         return year + '-' + month + '-' + day
//     },
//     convertDateFromString: function(dateString) {
//         if (dateString) {
//             dateString = dateString.replace(/-/g, '/')
//             return new Date(dateString)
//         }
//     },
//     formatNumber: function(n) {
//         n = n.toString()
//         return n[1] ? n : '0' + n
//     },
//     setCookie: function(cname, cvalue, exdays) {
//         var d = new Date()
//         d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000))
//         var expires = 'expires=' + d.toUTCString()
//             // console.info(cname + '=' + cvalue + '; ' + expires)
//         document.cookie = cname + '=' + cvalue + '; ' + expires
//             // console.info(document.cookie)
//     },
//     getCookie: function(cname) {
//         var name = cname + '='
//         var ca = document.cookie.split(';')
//         for (var i = 0; i < ca.length; i++) {
//             var c = ca[i]
//                 // console.log(c)
//             while (c.charAt(0) === ' ') c = c.substring(1)
//             if (c.indexOf(name) !== -1) {
//                 return c.substring(name.length, c.length)
//             }
//         }
//         return ''
//     },
//     // 清除cookie
//     clearCookie: function() {
//         this.setCookie('username', '', -1)
//     },
//     checkPhone: function(phone) {
//         if (!(/^1(3|4|5|7|8|9)\d{9}$/.test(phone))) {
//             return false
//         }
//         return true
//     }

 }
export default utils