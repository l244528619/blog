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
  </div>
</template>
<script>
export default {
  name: "index",
  data() {
    return {
         navimg: require("../../assets/img/header.png"),
           // tab
      cardArr: [
        {
          id: 0,
          name: "像我这样莫名其妙的人,会不会有人心疼 ---毛不易《像我这样的人》",
          imgCard: require("../../assets/img/maobuyi1.jpg"),
        },
        {
          id: 1,
          name:
            "听雨声 数几声 风会来 风筝误 悟了一句情似露珠 谁约我 又在这 风烟处风筝误",
          imgCard: require("../../assets/img/fengzhengwu.jpg"),
        },
        {
          id: 2,
          name: "在那座阴雨的小城里 我从未忘记你 成都 带不走的 只有你 ",
          imgCard: require("../../assets/img/zhaolei.jpg"),
        },
        {
          id: 3,
          name:
            "清浅池塘边　重生破土的冲动 天地正玲珑　殡葬了飞虫 迢迢河汉间　有磷火坠地如彗锋 奢望着　能生死相拥",
          imgCard: require("../../assets/img/yinlin.jpg"),
        },
      ],
    };
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

</style>
