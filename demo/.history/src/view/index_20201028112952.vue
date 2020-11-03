<template>
  <div class="hello">
    <header>
      <div class="logoimg">
        <img :src="logoImg" height="42px" />
      </div>
      <div class="headerNav">
        <el-row>
          <el-button icon="el-icon-search" type="text"></el-button>
          <el-button icon="el-icon-edit" type="text"></el-button>
          <el-button icon="el-icon-check" type="text"></el-button>
          <el-button icon="el-icon-message" type="text"></el-button>
          <el-button v-if="logiout" type="text" @click="logins">登录</el-button>

          <el-button type="text" v-if="signIn">{{ admin }},你好</el-button>
          <el-button type="text" v-if="signIn" @click="signOut">退出</el-button>
        </el-row>
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
                  <el-button class="registerBtn" type="text"
                    >立即注册</el-button
                  >
                </el-form-item>
              </el-form>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>

    <nav class="navBackground">
      <div class="navImage">
        <img class="img" :src="navimg" />
      </div>

      <div class="navText">
        <!-- <img :src="navimg" height="700px" /> -->
        <div class="navwriting">
          <p>
            风筝误
            <br />&nbsp;&nbsp;悟满相思挂苍苔
          </p>
        </div>
      </div>
    </nav>
    <main>
      <div class="maincenter">
        <div class="centerP">
          <p class="centerEssay">风筝误~随笔</p>
          <hr />
          <p>一抹笙香 吹梦成真</p>
          <p>风筝误 误了梨花花又开</p>
          <p>风筝误 捂了金钗雪里埋</p>
          <p>风筝误 悟满相思挂苍苔</p>
          <!-- <p icon="">jjj</p> -->
          <!-- <i class="el-icon-bicycle"></i> -->
        </div>
        <div class="centerCard">
          <el-carousel :interval="4000" type="card" height="700px">
            <el-carousel-item v-for="item in cardArr" :key="item.id">
              <img :src="item.imgCard" alt width="904px" />
              <h2 class="medium">{{ item.name }}</h2>
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import vLogin from "@/components/login";
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
      signIn: true,
      logoImg: require("../assets/img/logo.png"),
      navimg: require("../assets/img/header.png"),
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
      // tab
      cardArr: [
        {
          id: 0,
          name: "像我这样莫名其妙的人,会不会有人心疼 ---毛不易《像我这样的人》",
          imgCard: require("../assets/img/maobuyi1.jpg"),
        },
        {
          id: 1,
          name:
            "听雨声 数几声 风会来 风筝误 悟了一句情似露珠 谁约我 又在这 风烟处风筝误",
          imgCard: require("../assets/img/fengzhengwu.jpg"),
        },
        {
          id: 2,
          name: "在那座阴雨的小城里 我从未忘记你 成都 带不走的 只有你 ",
          imgCard: require("../assets/img/zhaolei.jpg"),
        },
        {
          id: 3,
          name:
            "清浅池塘边　重生破土的冲动 天地正玲珑　殡葬了飞虫 迢迢河汉间　有磷火坠地如彗锋 奢望着　能生死相拥",
          imgCard: require("../assets/img/yinlin.jpg"),
        },
      ],
      dialogTableVisible: false,
      // store,
    };
  },
  mounted() {
    this.getlogin();
  },
  methods: {
    ...mapMutations(["homeSet", "headerSet"]),
    async getlogin() {
      const coo = await this.$api.liveApi.getCurrentLoginUser();
      if (coo == "请登录后重试") {
        this.signIn = false;
      } else {
        this.logiout = false;
        this.admin = coo.msg.userName;
      }
      // this.$message.error(coo);
      console.log(coo);
    },
    logins() {
      console.log(11111111111111111);
      this.dialogTableVisible = true;
    },
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
            this.homeSet(this.ruleForm.userName)
          );
          this.homeSet(this.ruleForm.userName);
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
     async signOut(){
       let coo= await this.$api.liveApi.getLogout()
      
    }
  },
  components: {
    vLogin,
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
  align-content: center;
}
/* nav */
nav.navBackground {
  /* height: 100%; */
  background-image: url("../assets/img/headerbackground.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: right bottom;
  display: flex;
  z-index: 9999;
  align-content: flex-end;
}

.navBackground .navImage {
  padding-top: 150px;
  flex: 1;
  margin-left: 20%;
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
  padding-left: 50px;
  font-size: 80px;
  color: #999;
  font-family: "方正舒体";
}
/* main */

.maincenter {
  padding: 200px 100px 0 100px;
  background: rgba(220, 246, 248, 0.3);
  margin-bottom: 1500px;
  text-align: center;
  /* font-family: "方正舒体"; */
}
.centerP {
  margin-bottom: 100px;
}
.centerP p {
  margin: 10px;
  color: #475669;
}
.centerP .centerEssay {
  font-family: "方正舒体";
  margin-bottom: 20px;
  font-size: 55px;
  color: #666;
}
.el-carousel__item {
  background: rgba(230, 250, 252, 0.8);
}
.el-carousel__item h2 {
  text-align: center;
  color: #333;
  /* font-size: 20px; */
  /* opacity: 0.75; */
  margin: 50px;
}
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
