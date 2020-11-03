<template>
  <div id="app">
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
                    <el-button class="registerBtn" type="text" @click="sginOn"
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
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
  position: absolute;
  /* margin-top: 60px; */
}
</style>
