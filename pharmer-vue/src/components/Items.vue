<template>
  <div class="p-2">
    <div class="flex justify-between">
      <input
        class="flex-grow max-w-xs border rounded py-2 px-3
        text-gray-700 leading-tight font-hairline focus:outline-none"
        type="text"
        placeholder="Cauta"
        v-model="searchString"
      />
      <button
        class="rounded  focus:outline-none ml-2"
        @click="showAddForm = !showAddForm"
      >
        <svg
          class="h-6 w-6 fill-current text-green-400 hover:text-green-500 active:text-green-600"
          viewBox="0 0 20 20"
        >
          <path
            d="M15 9h-3v2h3v3h2v-3h3V9h-3V6h-2v3zM0 3h10v2H0V3zm0 8h10v2H0v-2zm0-4h10v2H0V7zm0 8h10v2H0v-2z"
            fill-rule="evenodd"
          />
        </svg>
      </button>
    </div>
    <transition name="list">
      <div v-if="showAddForm">
        <div class="flex justify-between items-center mt-2">
          <input
            class="flex-grow max-w-xs border rounded py-2 px-3
        text-gray-700 leading-tight font-hairline focus:outline-none"
            type="text"
            placeholder="Adauga"
            v-model="item.name"
          />
          <div class="flex items-end items-center">
            <v-date-picker
              v-model="item.expirationDate"
              :popover="{ placement: 'bottom-end', visibility: 'click' }"
            >
              <button
                class="ml-2 p-2 bg-green-500 hover:bg-green-600 active:bg-green-700 text-white rounded focus:outline-none"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                  class="w-5 h-5 fill-current"
                >
                  <path
                    d="M1 4c0-1.1.9-2 2-2h14a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V4zm2 2v12h14V6H3zm2-6h2v2H5V0zm8 0h2v2h-2V0zM5 9h2v2H5V9zm0 4h2v2H5v-2zm4-4h2v2H9V9zm0 4h2v2H9v-2zm4-4h2v2h-2V9zm0 4h2v2h-2v-2z"
                  />
                </svg>
              </button>
            </v-date-picker>
            <button
              class="ml-2 rounded focus:outline-none disabled:opacity-25"
              type="button"
              :disabled="!formValid"
              :class="{ 'cursor-not-allowed': !formValid }"
              @click="addItem(item)"
            >
              <svg
                class="h-6 w-6 fill-current text-green-400 hover:text-green-500 active:text-green-600"
                viewBox="0 0 20 20"
              >
                <path
                  d="M11 9V5H9v4H5v2h4v4h2v-4h4V9h-4zm-1 11c5.52285 0 10-4.47715 10-10S15.52285 0 10 0 0 4.47715 0 10s4.47715 10 10 10zm0-2c4.41828 0 8-3.58172 8-8s-3.58172-8-8-8-8 3.58172-8 8 3.58172 8 8 8z"
                  fill-rule="evenodd"
                />
              </svg>
            </button>
          </div>
        </div>
      </div>
    </transition>

    <transition-group name="list">
      <ItemCard
        v-for="item in filteredList"
        :key="item.id"
        :item="item"
        @delete-item="deleteItem"
        @edit-item="editItem"
        class="border rounded-lg mt-2 bg-white shadow-sm"
        :class="{
          'bg-gray-300': item.expired,
          'bg-orange-200': item.soonToExpire
        }"
      >
      </ItemCard>
    </transition-group>
    <span class="m-1 text-gray-600 text-sm italic">
      {{ filteredList.length }} produse in total</span
    >
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { Item } from "@/interfaces/Item";
import { ItemApi } from "@/interfaces/ItemApi";
import ItemCard from "@/components/ItemCard.vue";

@Component({ components: { ItemCard } })
export default class Items extends Vue {
  private items: Item[] = [];
  private item: Item = { name: "", expirationDate: "" };
  private showAddForm = false;
  private searchString = "";

  async mounted(): Promise<void> {
    this.items = await ItemApi.getAllitems();
  }

  async addItem(newItem: Item) {
    // const added = await ItemApi.addItem(newItem);
    // this.items.push(added);
    await ItemApi.addItem(newItem);
    this.items = await ItemApi.getAllitems();
    this.item.name = "";
    this.item.expirationDate = "";
  }

  editItem(item: Item) {
    ItemApi.editItem(item);
  }

  deleteItem(item: Item) {
    ItemApi.removeItem(item);
    this.items = this.items.filter(it => it.id !== item.id);
  }

  get filteredList() {
    return this.items.filter(i => {
      return i.name.toLowerCase().includes(this.searchString.toLowerCase());
    });
  }

  get formValid() {
    if (this.item.name && this.item.expirationDate) {
      return true;
    }
    return false;
  }
}
</script>
<style scoped></style>
