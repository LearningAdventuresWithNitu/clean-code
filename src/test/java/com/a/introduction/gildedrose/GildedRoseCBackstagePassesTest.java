package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GildedRoseCBackstagePassesTest {
	
	private static final int SELLIN_LESS_THAN_5 = 4;
	private static final int SELLIN_LESS_THAN_10 = 7;
	private static final int SELLIN_GREATER_THAN_10 = 15;
	private static final int QUALITY = 3;
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

	@Test
	void backstagePassesBeyond10Days_qualityIncreasedByOne() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10, QUALITY);
		
		// invoke
		app.updateQuality();

		// verify
		Item expectedItem = new Item(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10 - 1, QUALITY + 1);

		assertItem(expectedItem, app.items[0]);
	}

	
	@Test
	void backstagePassesBetween5And10Days_qualityIncreasedByTwo() {

		// setup
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_LESS_THAN_10, QUALITY);
		
		// invoke
		app.updateQuality();
		
		// verify
		Item expectedItem = new Item(BACKSTAGE_PASSES, SELLIN_LESS_THAN_10 - 1, QUALITY + 2);

		assertItem(expectedItem, app.items[0]);
	}

	@Test
	void backstagePassesLessThan5Days_qualityIncreasedByThree() {
		
		// setup
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_LESS_THAN_5, QUALITY);
		
		// invoke
		app.updateQuality();
		
		// verify
		Item expectedItem = new Item(BACKSTAGE_PASSES, SELLIN_LESS_THAN_5 - 1, QUALITY + 3);

		assertItem(expectedItem, app.items[0]);

	}
	
	private void assertItem(Item expectedItem, Item actualItem) {
		assertEquals(expectedItem.name, actualItem.name);
		assertEquals(expectedItem.sellIn, actualItem.sellIn);
		assertEquals(expectedItem.quality, actualItem.quality);
	}
	
	private GildedRose createGildedRoseWithOneItem(String backstagePasses, int unexpiredSellin, int quality) {
		Item item = new Item(backstagePasses, unexpiredSellin, quality);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}
}