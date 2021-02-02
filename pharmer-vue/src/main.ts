import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "./assets/css/main.css";
import VDatePicker from "v-calendar";
import Toasted from "vue-toasted";

Vue.use(Toasted, {
  theme: "bubble",
  position: "top-center",
  duration: 3000
});

Vue.use(VDatePicker);

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
