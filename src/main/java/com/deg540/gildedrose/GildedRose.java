package com.deg540.gildedrose;

import java.util.ArrayList;
import java.util.List;


public class GildedRose {
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        updateQuality(items);
    }

    static List<Item> updateQuality(List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).getName())) {
                items.get(i).setSellIn(items.get(i).getSellIn() - 1);
            }

            if (isStandarProduct(items, i) && items.get(i).getQuality() > 0) {
                items.get(i).setQuality(items.get(i).getQuality() - 1);
            }

            if ("Aged Brie".equals(items.get(i).getName()) && items.get(i).getQuality() < 50) {
                items.get(i).setQuality(items.get(i).getQuality() + 1);
                if (items.get(i).getQuality() < 50 && items.get(i).getSellIn() < 0) {
                    items.get(i).setQuality(items.get(i).getQuality() + 1);
                }
            }

            if ("Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName())) {
                if (items.get(i).getQuality() < 50) {
                    items.get(i).setQuality(items.get(i).getQuality() + 1);
                    if (items.get(i).getSellIn() < 11) {
                        if (items.get(i).getQuality() < 50) {
                            items.get(i).setQuality(items.get(i).getQuality() + 1);
                        }
                    }
                    if (items.get(i).getSellIn() < 6) {
                        if (items.get(i).getQuality() < 50) {
                            items.get(i).setQuality(items.get(i).getQuality() + 1);
                        }
                    }
                }
            }

            if (items.get(i).getSellIn() < 0 && !"Aged Brie".equals(items.get(i).getName())) {
                if (!"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName())) {
                    if (items.get(i).getQuality() > 0) {
                        if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).getName())) {
                            items.get(i).setQuality(items.get(i).getQuality() - 1);
                        }
                    }
                } else {
                    items.get(i).setQuality(0);
                }
            }
        }
        return items;
    }

    private static boolean isStandarProduct(List<Item> items, int i) {
        return "Elixir of the Mongoose".equals(items.get(i).getName()) ||
                "+5 Dexterity Vest".equals(items.get(i).getName()) ||
                "Conjured Mana Cake".equals(items.get(i).getName());
    }
}