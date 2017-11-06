package com.deg540.gildedrose;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GildedRoseShould {

    @Test
    public void update_items() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("Elixir of the Mongoose", 4, 6)));
        assertThat(updatedItems.get(1), is(new Item("+5 Dexterity Vest", 9, 19)));
        assertThat(updatedItems.get(2), is(new Item("Aged Brie", 1, 1)));
        assertThat(updatedItems.get(3), is(new Item("Sulfuras, Hand of Ragnaros", 0, 80)));
        assertThat(updatedItems.get(4), is(new Item("Backstage passes to a TAFKAL80ETC concert", 14, 21)));
        assertThat(updatedItems.get(5), is(new Item("Conjured Mana Cake", 2, 5)));
    }
}