<template>
  <div>
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
          <el-button type="text">登录</el-button>
        </el-row>
      </div>
    </header>
    <nav class="navBackground">
      <div class="navImage">
        <img :src="navimg" height="700px" />
      </div>

      <div class="navText">
        <!-- <img :src="navimg" height="700px" /> -->
        <div class="navwriting">记录点滴生活  |  记录点滴生活
          <br>记录点滴生活  |  记录点滴生活
          <br>记录点滴生活  |  记录点滴生活
          <br>记录点滴生活  |  记录点滴生活
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  data() {
    return {
      logoImg: require("../assets/img/logo.png"),
      navimg: require("../assets/img/header.png")
    };
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header {
  padding: 10px 0;
  height: 42px;
  display: flex;
  justify-content: space-around;
  overflow: hidden;
  border-bottom: 1px solid rgb(209, 209, 209);
}
.logoimg {
  line-height: 42px;
}
.el-button--text {
  line-height: 14px;
  color: rgb(126, 127, 128);
}
header .headerNav {
  /* line-height: 20px; */
  align-content: center;
}
nav.navBackground {
  width: 100%;
  /* padding-top: 50px; */
  height: 800px;
  background: url("../assets/img/headerbackground.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  display: flex;
}

.navBackground .navImage {
  /* height: 700px; */
  padding-top: 100px;
  align-items: flex-end;
  flex: 1;
  -webkit-box-flex: 1;
  padding-left: 200px;
  overflow: hidden;
}

.navBackground .navText {
  /* height: 500px; */
  /* border: 1px solid #999; */
  flex: 1;
  -webkit-box-flex: 1;
  margin-right: 400px;
}
.navwriting {
  /* display: flex;
  justify-content: center;
  align-items:center; */
  font-size: 40px;
  margin-top: 300px;
  color: #666;
  /* border: 1px solid #999; */

}
</style>
