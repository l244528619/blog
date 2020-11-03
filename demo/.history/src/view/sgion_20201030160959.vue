<template>
  <div class="sgion">
    <nav class="navBackground">
      <div class="navImage">
        <img class="img" :src="navimg" />
      </div>

      <div class="navText">
        <!-- <img :src="navimg" height="700px" /> -->
        <div class="navwriting">
            <p>ssssssssssss</p>
            <p>ssssssssssss</p>
            <p>ssssssssssss</p>
            <p>ssssssssssss</p>
            <p>ssssssssssss</p>
            <p>ssssssssssss</p>
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
      navimg: require("../assets/img/header.png"),
    };
  },
  mounted() {},
  methods: {},
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
  background-color: white;
  margin: 0 150px;
  height: 80%;
  font-size: 80px;
  color: #999;
  font-family: "方正舒体";
}
</style>
