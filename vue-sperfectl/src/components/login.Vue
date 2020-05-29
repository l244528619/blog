<template>
  <div class="login">
    <div class="navBackground">
      <div class="navImage">
        <img class="img" :src="navimg" />
      </div>

      <div class="navTable">
        <div class="navTab">
          <div class="useName">用户名密码登录</div>
          <el-form :model="ruleForm"  :rules="rules" ref="ruleForm">
            <el-form-item prop="name">
              <el-input v-model="ruleForm.name" autocomplete="off" placeholder="用户名登录"></el-input>
            </el-form-item>
            <el-form-item prop="pass">
              <el-input type="password" v-model="ruleForm.pass" autocomplete="off" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="text" >自动登录</el-button>
              <el-button type="text" >忘记密码？</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    //   用户名限制
    var validateUsername = (rule, value, callback)=> {
      if (value.length < 5) {
        return callback(new Error("用户名不得小于5个字符!"));
      } else {
        callback();
      }
    }
    //密码
    var validatePass = (rule, value, callback) => {
      if (value.length < 6 || value.length > 18) {
        return callback(new Error("用户名不得小于6个或大于20个字符!"));
      } else {
        callback();
      }
    };

    // var validateEmail = (rule, value, callback) => {
    //   const emailReg = /^(([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5}){1,25})$/;
    //   if (!value) {
    //     return callback(new Error("邮箱不能为空!!"));
    //   }
    //   setTimeout(() => {
    //     if (!emailReg.test(value)) {
    //       return callback(new Error("邮箱格式错误"));
    //     } else {
    //       callback();
    //     }
    //   }, 100);
    // };

    return {
      logoImg: require("../assets/img/logo.png"),
      navimg: require("../assets/img/header.png"),
      ruleForm: {
        pass: "",
        name: ""
      },
      rules: {
        pass: [{ required: true, validator: validatePass, trigger: "blur" }],
        name: [{  required: true, validator: validateUsername, trigger: "blur" }]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
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
  width: 400px;
  height: 500px;
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
  width: 300px;
}
button.el-button.el-button--primary {
  width: 300px;
}
</style>
