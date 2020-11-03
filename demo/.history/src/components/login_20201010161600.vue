<template>
  <div class="login">
    <div class="navBackground">
      <div class="navImage">
        <img class="img" :src="navimg"  height="500px"/>
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
              <el-button class="registerBtn" type="text">立即注册</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import Home from "@/components/Home/home";
// import User from "@/components/UserPage/user";

export default {
  name: "login",
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
      ruleForm: {
        password: "",
        userName: "",
      },
      //表单验证规则对象
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
      flag: "",
      // plain:true
    };
  },
  mounted() {
    //  this.getLogin(this.ruleForm.userName, this.ruleForm.password);
  },
  methods: {
    login() {
      this.$refs.ruleForm.validate(async (valid) => {
        let { msg, flag } = await this.$api.liveApi.getLogin({
          userName: this.ruleForm.userName,
          password: this.ruleForm.password,
        });
        if (valid && flag) {
          // this.getLogin(this.ruleForm.userName, this.ruleForm.password);
          console.log("成功", this.flag);
        } else {
          // this.getLogin(this.ruleForm.userName, this.ruleForm.password);
          // console.log("失败")
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
    // user 和 admin页面跳转
    // getUaerOrAdmin(useName) {
    //   if ((this.ruleForm.userName = "admin")) {
    //     console.log(this.ruleForm.userName);
    //     this.$router.push({
    //       path: "/home",
    //       query: { username: this.ruleForm.userName }
    //     });
    //     console.log(this.ruleForm.userName);
    //   } else {
    //     this.$router.push("/user");
    //   }
    // }
  },
  components: {
    // Home,
    // User
  },
};
</script>

<style scoped>
.login {
  width: 100%;
  height: 100%;
}

/* nav */
.navBackground {
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
.navBackground .navImage {
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
  width:90%;
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
</style>
