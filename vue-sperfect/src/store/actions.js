import utils from '../util/util.js';

export default {
  // 获取用户所有信息
  getCurrentUserInfo: ({commit, state}) => {
    return utils.post("/currentLoginUser", {}, ({data}) => {
      commit('setUserName', data);
    });
  },
  login: ({commit, parms}) => {
    return utils.post("/login", parms, ({data}) => {
      commit('setUserName', data);
    });
  },
}
