package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GildedRoseBAgedBrieTest {

	private static final int MAX_QUALITY = 50;
	private static final int EXPIRED_SELLIN = -1;
	private static final int QUALITY = 3;
	private static final int UNEXPIRED_SELLIN = 4;
	private static final String AGED_BRIE = "Aged Brie";

	@Test
	void unexpiredAgedBrieItem_qualityIncreasesByOne() {

		// setup
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, UNEXPIRED_SELLIN, QUALITY);

		// invoke
		app.updateQuality();

		// verify
		Item expectedItem = new Item(AGED_BRIE, UNEXPIRED_SELLIN - 1, QUALITY + 1);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	void expiredAgedBrieItem_qualityIncreasesByTwo() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELLIN, QUALITY);
		
		// invoke
		app.updateQuality();

		// verify
		Item expectedItem = new Item(AGED_BRIE, EXPIRED_SELLIN - 1, QUALITY + 2);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	void unexpiredAgedBrieItem_qualityDoesNotGoBeyondMax() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, UNEXPIRED_SELLIN, MAX_QUALITY);
		
		// invoke
		app.updateQuality();

		// verify
		Item expectedItem = new Item(AGED_BRIE, UNEXPIRED_SELLIN - 1, MAX_QUALITY);

		assertItem(expectedItem, app.items[0]);
	}
	
	private GildedRose createGildedRoseWithOneItem(String itemType, int sellin, int quality) {
		Item item = new Item(itemType, sellin, quality);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}
	
	private void assertItem(Item expectedItem, Item actuaItem) {
		assertEquals(expectedItem.name, actuaItem.name);
		assertEquals(expectedItem.sellIn, actuaItem.sellIn);
		assertEquals(expectedItem.quality, actuaItem.quality);
	}
}
