export default {
    setUserName (states, data) {
        if(data.flag){
            states.userName = data.msg.userName;
        } else {
            states.occurError = true; //服务器已发生了错误
        }
    },
    setOccurError (states, data) {
          states.occurError = data;
    }
}
