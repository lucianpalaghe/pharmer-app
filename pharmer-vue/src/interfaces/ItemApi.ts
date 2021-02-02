import API from "@/api";
import { Item } from "./Item";

export abstract class ItemApi {
  static async getAllitems(): Promise<Item[]> {
    const response = await API.get<Item[]>("items");
    return response.data;
  }

  static async addItem(item: Item) {
    const response = await API.post("items", item);
    return response.data;
  }

  static async editItem(item: Item) {
    const response = await API.put(`items/${item.id}`, item);
    return response.data;
  }

  static async removeItem(item: Item): Promise<Item> {
    const response = await API.delete(`items/${item.id}`);
    return response.data;
  }
}
