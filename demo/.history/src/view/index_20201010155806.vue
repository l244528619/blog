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
          <el-button v-show="logiout" type="text" @click="logins"
            >登录</el-button
          >
          <el-button type="text" v-show="signIn">XXX,你好</el-button>
          <el-button type="text" v-show="signIn">退出</el-button>
        </el-row>
      </div>
    </header>

    <el-dialog title="收货地址" :visible.sync="dialogTableVisible">
      <v-login/>
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
import vLogin from "login"
export default {
  name: "index",
  data() {
    return {
      logoImg: require("../assets/img/logo.png"),
      navimg: require("../assets/img/header.png"),
      logiout: true,
      signIn: true,
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
    async getlogin() {
      this.dialogTableVisible = true;
      const coo = await this.$api.liveApi.getCurrentLoginUser();
      if (coo == "请登录后重试") {
        this.signIn = false;
      }
      // this.$message.error(coo);
    },
    logins() {
      this.$router.push({ name: "login" });
      //  console.log(  this.$router.push({name:'login'}))
    },
  },
  components: {
    vLogin
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
</style>
