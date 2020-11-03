<template>
  <div class="hello">
    <header>
      <div class="logoimg">
        <img :src="logoImg" height="42px" />
      </div>
      <div class="headerNav">
        <div class="icon">
          <!-- <el-row> -->
          <el-button icon="el-icon-search" type="text"></el-button>
          <el-button icon="el-icon-edit" type="text"></el-button>
          <el-button icon="el-icon-check" type="text"></el-button>
          <el-button icon="el-icon-message" type="text"></el-button>
          <!--  </el-row> -->
        </div>
        <div class="logins" v-if="logiout">
          <!-- <el-row> -->
          <el-button type="text" @click="logins">登录</el-button>
          <el-button type="text" @click="register">注册</el-button>
          <!-- </el-row> -->
        </div>
        <div v-else class="logOut">
          <el-button type="text" @click="goHome">{{ admin }},你好</el-button>
          <el-button type="text" @click="signOut">退出</el-button>
        </div>
      </div>
    </header>

    <el-dialog :visible.sync="dialogTableVisible" class="elDialog">
      <!-- <v-login /> -->
      <div class="login">
        <div class="navBack">
          <div class="navImages">
            <img class="img" :src="navimg" height="500px" />
          </div>

          <div class="navTable">
            <div class="navTab">
              <div class="useName">用户名密码登录</div>
              <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
                <el-form-item prop="userName">
                  <el-input
                    v-model="ruleForm.userName"
                    prefix-icon="el-icon-s-custom"
                    autocomplete="off"
                    placeholder="请输入用户名"
                  ></el-input>
                </el-form-item>
                <el-form-item prop="password">
                  <el-input
                    type="password"
                    prefix-icon="el-icon-shopping-bag-1"
                    v-model="ruleForm.password"
                    autocomplete="off"
                    placeholder="请输入密码"
                  ></el-input>
                </el-form-item>
                <el-form-item class="loginAuto">
                  <el-checkbox v-model="checked">自动登录</el-checkbox>
                </el-form-item>
                <el-form-item class="logoTo">
                  <el-button type="primary" @click="login">登录</el-button>
                  <!-- :plain="plain" -->
                </el-form-item>
                <el-form-item>
                  <el-button type="text">忘记密码？</el-button>
                  <el-button class="registerBtn" type="text" @click="register"
                    >立即注册</el-button
                  >
                </el-form-item>
              </el-form>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>
   <router-view />
  </div>
</template>

<script>
// import vLogin from "@/components/login";
import { mapMutations } from "vuex";
export default {
  name: "index",
  data() {
    //   用户名限制
    var validateUsername = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入用户名"));
      } else {
        callback();
      }
    };
    //密码
    var validatePass = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入密码"));
      } else if (value.length < 5 || value.length > 18) {
        return callback(new Error("密码不得小于6个或大于20个字符!"));
      } else {
        callback();
      }
    };

    return {
      logoImg: require("../assets/img/logo.png"),
      navimg: require("../assets/img/header.png"),
      logiout: true,
      ruleForm: {
        password: "",
        userName: "",
      },
      // 表单验证规则对象
      rules: {
        password: [
          { required: true, validator: validatePass, trigger: "blur" },
        ],
        userName: [
          { required: true, validator: validateUsername, trigger: "blur" },
        ],
      },
      checked: false,
      message: "",
      admin: "xx",
      flag: "",
   
      dialogTableVisible: false,
      // store,
    };
  },
  mounted() {
    this.getlogin();
  },
  methods: {
    ...mapMutations(["userSet", "headerSet"]),
    //判断当前页面是否登录
    async getlogin() {
      const { flag, msg } = await this.$api.liveApi.getCurrentLoginUser();
      if (flag) {
        this.logiout = false;
        this.admin = msg.userName;
        // this.goHome(msg.userName);
      }
    },
    //跳转home页
    goHome() {
      this.userSet(this.admin);
      this.$router.push({
        name: "home",
      });
    },
    //弹出登录窗口
    logins() {
      console.log(11111111111111111);
      this.dialogTableVisible = true;
    },
    //登录
    login() {
      this.$refs.ruleForm.validate(async (valid) => {
        let { msg, flag } = await this.$api.liveApi.getLogin({
          userName: this.ruleForm.userName,
          password: this.ruleForm.password,
        });
        if (valid && flag) {
          this.headerSet(msg); // ...mapMutations(['headerSet']),this.headerSet(msg)  等价于 this.$store.commit('headerSet',msg)
          console.log(
            "成功11111111",
            this.flag,
            msg,
            this.ruleForm.userName,
            this.userSet(this.ruleForm.userName)
          );
          this.userSet(this.ruleForm.userName);
          this.logiout = false;
          this.$router.push({
            name: "home",
          });
        } else {
          console.log("失败", this.flag);
          console.log(msg);
          this.$message({
            showClose: true,
            message: msg,
            type: "error",
          });
          return false;
        }
      });
    },
    // 退出
    async signOut() {
      let { flag, msg } = await this.$api.liveApi.getLogout();
      console.log(flag, msg);
      if (flag) {
        this.logiout = true;
        sessionStorage.removeItem('headerStore');
        sessionStorage.removeItem('userName');
      }
    },
    //注册  
    register(){
      console.log(1111)
      this.$router.push({
        path:"index/sgion"
      })
    }

  },
  components: {
    // vLogin,
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello {
  width: 100%;
  height: 100%;
}
header {
  width: 100%;
  padding: 10px 0;
  height: 42px;
  background: rgba(232, 232, 233, 0.3);
  display: flex;
  justify-content: space-around;
  overflow: hidden;
  border-bottom: 1px solid rgb(209, 209, 209);
  position: fixed;
  z-index: 999;
}
.logoimg {
  line-height: 42px;
}
.el-button--text {
  line-height: 14px;
  color: rgb(126, 127, 128);
}
header .headerNav {
  /* align-content: center; */
  display: flex;
  justify-content: flex-end;
  width: 30%;
}
.headerNav .icon {
  display: flex;
}
.headerNav .logins {
  padding-left: 10px;
  display: flex;
}
.headerNav .logOut {
  padding-left: 10px;
}

/* 登录 */
.login {
  width: 100%;
  height: 100%;
}

/* nav */
.navBack {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("../assets/img/headerbackground.jpg");
  height: 100%;
  background-size: cover;
}
/* .navImage {
    width: 100%;
    height: 100%;
} */
.navBack .navImages {
  flex: 2;
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
  height: 100%;
  /* justify-content: space-around; */
}
.navTable {
  height: 100%;
  margin-left: 50px;
  flex: 3;

  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.navTable .navTab {
  width: 90%;
  height: 60%;
  background: rgba(244, 246, 247, 0.6);
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: column;
}
.useName {
  margin: 50px auto;
  font-size: 20px;
}
form.el-form {
  width: 80%;
}
button.el-button.el-button--primary {
  width: 100%;
}
.registerBtn {
  display: inline-block;
  margin-left: 150px;
}
.loginAuto {
  position: absolute;
  float: left;
  padding-bottom: 20px;
}
.logoTo {
  padding-top: 50px;
}
.elDialog {
  /* width: 50%; */
  min-width: 1480px;
}
</style>
