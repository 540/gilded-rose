package com.deg540.gildedrose;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GildedRoseShould {

    private List<Item> items;
    @Before
    public void setUp() throws Exception {
        items = new ArrayList<>();
    }

    @Test
    public void update_items() {
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("Elixir of the Mongoose", 4, 6)));
        assertThat(updatedItems.get(1), is(new Item("+5 Dexterity Vest", 9, 19)));
        assertThat(updatedItems.get(2), is(new Item("Conjured Mana Cake", 2, 5)));
        assertThat(updatedItems.get(3), is(new Item("Aged Brie", 1, 1)));
        assertThat(updatedItems.get(4), is(new Item("Sulfuras, Hand of Ragnaros", 0, 80)));
        assertThat(updatedItems.get(5), is(new Item("Backstage passes to a TAFKAL80ETC concert", 14, 21)));
    }

    @Test
    public void quality_of_an_item_degrades_twice_fast_when_sell_date_is_passed() throws Exception {
        items.add(new Item("+5 Dexterity Vest", -1, 2));
        items.add(new Item("Elixir of the Mongoose", -3, 5));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("+5 Dexterity Vest", -2, 0)));
        assertThat(updatedItems.get(1), is(new Item("Elixir of the Mongoose", -4, 3)));
    }

    @Test
    public void quality_is_degraded_by_1_in_standard_items() throws Exception {
        items.add(new Item("Elixir of the Mongoose", 2, 3));
        items.add(new Item("+5 Dexterity Vest", 3, 2));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("Elixir of the Mongoose", 1, 2)));
        assertThat(updatedItems.get(1), is(new Item("+5 Dexterity Vest", 2, 1)));
        assertThat(updatedItems.get(2), is(new Item("Conjured Mana Cake", 2, 5)));
    }

    @Test
    public void quality_of_an_item_is_never_negative() throws Exception {
        items.add(new Item("+5 Dexterity Vest", 1, 0));
        items.add(new Item("Elixir of the Mongoose", 1, 0));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("+5 Dexterity Vest", 0, 0)));
        assertThat(updatedItems.get(1), is(new Item("Elixir of the Mongoose", 0, 0)));
    }

    @Test
    public void increase_aged_brie_quality_when_it_gets_older() throws Exception {
        items.add(new Item("Aged Brie", 0, 0));
        items.add(new Item("Aged Brie", 3, 2));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("Aged Brie", -1, 2)));
        assertThat(updatedItems.get(1), is(new Item("Aged Brie", 2, 3)));
    }

    @Test
    public void quality_of_an_item_is_never_more_than_50() throws Exception {
        items.add(new Item("Aged Brie", -1, 50));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 9, 49));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("Aged Brie", -2, 50)));
        assertThat(updatedItems.get(1), is(new Item("Backstage passes to a TAFKAL80ETC concert", 8, 50)));
    }

    @Test
    public void sulfuras_never_decreases_sellin_or_quality() throws Exception {
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("Sulfuras, Hand of Ragnaros", 0, 80)));
    }

    @Test
    public void backstage_passes_increses_quality_by_2_when_less_than_10_sellin_days() throws Exception {
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("Backstage passes to a TAFKAL80ETC concert", 9, 2)));
    }

    @Test
    public void backstage_passes_increses_quality_by_3_when_less_than_5_sellin_days() throws Exception {
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 3)));
    }

    @Test
    public void backstage_passes_drops_to_0_when_less_than_0_sellin_days() throws Exception {
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5));

        List<Item> updatedItems = GildedRose.updateQuality(items);

        assertThat(updatedItems.get(0), is(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0)));
    }
}