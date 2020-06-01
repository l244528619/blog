<template>
  <div class="home">
    <el-container>
        <el-aside width="300px">Aside</el-aside>
     
      <el-container>
         <el-header  height="80px">
             <div class="headerUser">
               <div>{{ this.$route.query.username}} ,你好</div>
                  <div> <el-button type="text">退出登录</el-button></div>
             </div>
         </el-header>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
    <!-- <p>{{ this.$route.query.username}}</p> -->
  </div>
</template> 
<script>
export default {
  name: "home",
  data() {
    return {};
  },
  mounted() {},
  methods: {},
  components: {}
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
    display: flex;
    justify-content: space-between;
    height: 200px;
    box-shadow: 6px 4px 7px rgb(177, 176, 176); 
    margin-bottom: 2px;
    background-color: #ffffff;
    color: #333;
    /* text-align: center; */
    /* line-height: 60px; */
  }
  
  .el-aside {
    background-color: rgb(41, 41, 41);
    color: #333;
    text-align: center;
    /* line-height: 200px; */
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    /* line-height: 160px; */
  }
  
 
  
</style>