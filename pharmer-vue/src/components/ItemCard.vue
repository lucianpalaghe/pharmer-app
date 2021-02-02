<template>
  <div class="flex justify-between items-center px-2">
    <div>
      <div>
        <div
          class="font-semibold text-xl"
          :class="{ 'opacity-50': item.expired }"
          v-show="!editing"
          @click="enterEdit()"
        >
          {{ item.name }}
        </div>
        <input
          class="font-semibold text-xl bg-transparent w-11/12"
          type="text"
          ref="afterClick"
          v-model="item.name"
          v-show="editing"
          @blur="doneEditing()"
          @keyup.enter="doneEditing()"
        />
      </div>

      <span v-if="item.expired" class="font-light italic text-red-500 text-base"
        >A expirat in {{ item.expirationDate }}</span
      >
      <span v-else class="font-hairline text-gray-700 text-base"
        >Expira in {{ item.expirationDate }}</span
      >
    </div>
    <button
      class="py-2 px-2 ml-2 rounded block bg-red-600 hover:bg-red-700 active:bg-red-800"
      @click="deleteItem(item)"
    >
      <svg
        class="h-5 w-5"
        viewBox="0 0 20 20"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M2 2h16v2H2V2zm6-2h4l2 2H6l2-2zM3 6h14l-1 14H4L3 6zm5 2h1v10H8V8zm3 0h1v10h-1V8z"
          fill="#fff"
          fill-rule="evenodd"
        />
      </svg>
    </button>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Emit, Ref, Vue } from "vue-property-decorator";
import { Item } from "@/interfaces/Item";

@Component
export default class ItemCard extends Vue {
  @Prop() item: Item | undefined;
  @Ref() afterClick!: HTMLFormElement;

  private editing = false;

  @Emit()
  deleteItem() {
    return this.item;
  }

  @Emit()
  editItem() {
    return this.item;
  }

  enterEdit() {
    if (!this.editing && !this.item?.expired) {
      this.editing = true;
      this.$nextTick(function() {
        this.afterClick.focus();
      });
    }
  }

  doneEditing() {
    if (this.editing) {
      this.editing = false;
      this.editItem();
    }
  }
}
</script>

<style scoped></style>
