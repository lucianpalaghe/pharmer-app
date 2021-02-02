<template>
  <div class="p-2">
    <Items class="grid max-w-screen-sm" />
  </div>
</template>

<script>
// @ is an alias to /src
import Items from "@/components/Items.vue";

export default {
  name: "Home",
  components: {
    Items
  }
};
</script>
