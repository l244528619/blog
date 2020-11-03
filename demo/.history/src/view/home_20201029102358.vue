<template>
  <div class="home">
    <el-container>
      <el-aside width="300px">
        <!-- <img src="../../assets/img/goulaowang.jpg" width="150px" /> -->
        <el-image class="imageUrl" style="width: 100px" :src="url"></el-image>
        <div class="asideNav">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#000"
            text-color="#fff"
            active-text-color="#ffd04b"
          >
            <el-menu-item index="1">
              <i class="el-icon-location"></i>
              <span slot="title">权限管理</span>
            </el-menu-item>
            <el-menu-item index="2">
              <i class="el-icon-menu"></i>
              <span slot="title">导航二</span>
            </el-menu-item>
            <el-menu-item index="3">
              <i class="el-icon-document"></i>
              <span slot="title">导航三</span>
            </el-menu-item>
            <el-menu-item index="4">
              <i class="el-icon-setting"></i>
              <span slot="title">导航四</span>
            </el-menu-item>
          </el-menu>
        </div>
      </el-aside>

      <el-container>
        <el-header height="80px">
          <div class="headerUser">
             {{ admin}} ,你好
            <div>
              <el-button type="text" @click="signOut">退出登录</el-button>
            </div>
          </div>
        </el-header>
        <el-main>Main
          <div>
            ddddddddddddddddd
          </div>
        </el-main>
      </el-container>
    </el-container>
    <!-- <p>{{ this.$route.query.username}}</p> -->
  </div>
</template> 
<script>
import {mapState} from 'vuex'

export default {
  name: "home",
  data() {
    return {
      url: require("@/assets/img/goulaowang.jpg"),
    };
  },
  components: {},
  computed:{
    ...mapState({
    admin: state => state.userName,
  })
  
  },
  mounted() {
    console.log(window.sessionStorage.getItem('headerStore'))
     console.log(window.sessionStorage.getItem('userName'))
    // this.signOut()
    console.log(this.admin)
    // if(this.admin)
  },
   
  methods: {
    async signOut() {
     let {flag,msg} = await this.$api.liveApi.getLogout()
     console.log(msg)
     if(flag){
       this.$router.push("/")
     }

    },
    getLogout() {
      // this.utils.get("/logout", {}, function(res) {
      //   console.log(res);
      // });
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  },
 
};
</script>
<style  scoped>
.home {
  position: relative;
  top: 0;
  margin: 0;
  width: 100%;
  height: 100%;
}
section.el-container {
  height: 100%;
}

.el-header {
  /* height: 200px; */
  box-shadow: 6px 4px 7px rgb(177, 176, 176);
  margin-bottom: 2px;
  background-color: #ffffff;
  color: #333;
  /* text-align: center; */
  /* line-height: 60px; */
}
.headerUser {
  display: flex;
  justify-content: flex-end;
  align-content: center;
  line-height: 80px;
  font-size: 16px;
}

.el-aside {
  background-color:#000;
  color: #333;
  text-align: center;
  /* line-height: 200px; */
  padding-top: 50px;
}
ul.el-menu-vertical-demo.el-menu {
  border: 0;
}
.imageUrl {
  border-radius: 50%;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  /* line-height: 160px; */
}
</style>