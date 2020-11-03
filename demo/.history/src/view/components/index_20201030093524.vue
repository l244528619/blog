<template>
  <div>
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
  name: "index",
  data() {
    return {};
  },
  mounted() {},
  methods: {},
  created() {},
  components: {},
};
</script>
<style scoped>
/* nav */
nav.navBackground {
  /* height: 100%; */
  background-image: url("@/assets/img/headerbackground.jpg");
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
