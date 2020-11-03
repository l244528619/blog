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
          <el-button v-show="logiout" type="text" @click="login">登录</el-button>
          <el-button type="text" v-show="signIn">XXX,你好</el-button>
        </el-row>
      </div>
    </header>
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
export default {
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
    };
  },
};
</script>

