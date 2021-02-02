import Axios from "axios";
import Vue from "vue";

const ax = Axios.create({ baseURL: "http://192.168.1.9:8081/" });
// Add a response interceptor
ax.interceptors.response.use(
  function(response) {
    return response;
  },
  function(error) {
    Vue.toasted.error(error);

    return Promise.reject(error);
  }
);

export default ax;
