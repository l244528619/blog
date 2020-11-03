<template>
  <div class="sgion">
    <nav class="navBackground">
      <div class="navImage">
        <img class="img" :src="navimg" />
      </div>

      <div class="navText">
        <div class="navwriting">
          <div class="siginTitle">欢迎注册</div>
          <el-form
            label-position="left"
            :model="ruleForm"
            status-icon
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item label="昵称" prop="user">
              <el-input
                type="text"
                v-model="ruleForm.user"
                autocomplete="off"
                placeholder="请设置用户名"
              ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input
                type="password"
                v-model="ruleForm.pass"
                autocomplete="off"
                placeholder="设置登陆密码"
              ></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input
                type="password"
                v-model="ruleForm.checkPass"
                autocomplete="off"
                placeholder="再次输入密码"
              ></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="mobile">
              <el-input
                autocomplete="off"
                v-model.number="ruleForm.mobile"
                 placeholder="用于登录或找回密码"
              ></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="code">
              <div class="btnCode">
                <el-input
                  style="width: 200px"
                  v-model.number="ruleForm.code"     
                  autocomplete="off"
                   placeholder="请输入验证码"
                ></el-input>
                <div>hhhhhhh</div>
              </div>
            </el-form-item>
            <el-form-item class="goIndex">
              <el-button type="primary" @click="submitForm('ruleForm')"
                >注册</el-button
              >
              <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
            </el-form-item>
          </el-form>
        </div>
      </div>
    </nav>
  </div>
</template>
<script>
export default {
  name: "index",
  data() {
    // 用户名
    var userName = (rule, value, callback) => {
      console.log(value);
      var reg = new RegExp(/^[a-zA-Z0-9_-]{3,16}$/); //字符串正则表达式 4到14位（字母，数字，下划线，减号）
      if (!value) {
        return callback(new Error("用户名不能为空"));
      } else if (!reg.test(value)) {
        return callback(new Error("用户名格式不正确（字母，数字，下划线）"));
      } else {
        callback();
      }
    };
    //手机号
    var mobile = (rule, value, callback) => {
      var reg = new RegExp(/^[1]([3-9])[0-9]{9}$/);
      if (!value) {
        return callback(new Error("手机号码不能为空"));
      } else if (!reg.test(value)) {
        return callback(new Error("手机号格式不正确"));
      } else {
        callback();
      }
    };
    // 密码
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    // 确认密码
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    //验证码
    var verification = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入验证码"));
      }
    };

    return {
      navimg: require("../assets/img/header.png"),
      ruleForm: {
        user: "",
        pass: "",
        checkPass: "",
        mobile: "",
        code: "",
      },
      rules: {
        user: [{ required: true, validator: userName, trigger: "blur" }],
        pass: [{ required: true, validator: validatePass, trigger: "blur" }],
        checkPass: [
          { required: true, validator: validatePass2, trigger: "blur" },
        ],
        mobile: [{ required: true, validator: mobile, trigger: "blur" }],
        code: [{ required: true, validator: verification, trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.verificationCode();
  },
  methods: {
    submitForm(formName) {
      // this.$refs[formName].validate((valid) => {
      //   if (valid) {
      //     alert("submit!");
      //   } else {
      //     console.log("error submit!!");
      //     return false;
      //   }
      // });
    },
    resetForm(formName) {
      // this.$refs[formName].resetFields();
    },
    // 获取验证码
    async verificationCode() {
      let coo = await this.$api.liveApi.getVerification();
      console.log(coo);
    },
  },
  created() {},
  components: {},
};
</script>
<style scoped>
.sgion {
  position: relative;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
/* nav */
nav.navBackground {
  height: 100%;
  width: 100%;
  background-image: url("../assets/img/headerbackground.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: right bottom;
  display: flex;
  z-index: 9999;
  align-content: flex-end;
}

.navBackground .navImage {
  padding-top: 50px;
  flex: 1;
  margin-left: 10%;
}
.img {
  animation: img 2s ease 0s 1;
  -webkit-animation: img 2s ease 0s 1;
}

@keyframes img {
  from {
    margin-left: -20%;
  }
  to {
    margin-left: 0px;
  }
}
@-webkit-keyframes img {
  from {
    margin-left: -20%;
  }
  to {
    margin-left: 0px;
  }
}

.navBackground .navText {
  margin: auto 0;
  flex: 2;
}

.navwriting {
  background-color: rgba(255, 255, 255, 0.5);
  margin: 0 250px;
  height: 700px;
  padding: 0 50px;
  display: flex;
  flex-direction: column;
  align-items: baseline;

  /* font-size: 80px; */
  /* color: #999; */
}
.siginTitle {
  padding: 50px 0 20px 0;
  font-size: 35px;
  font-weight: bold;
}
.el-input {
  width: 350px;
}
.btnCode {
  display: flex;
  flex-direction: initial;
  justify-content: space-between;
}
.goIndex {
  margin: 0;
  width: 100%;
}
</style>
