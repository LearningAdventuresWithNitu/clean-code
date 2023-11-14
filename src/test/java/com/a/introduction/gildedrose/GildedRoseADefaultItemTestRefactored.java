package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTestRefactored {
	
	private static final int EXPIRED_SELLIN = -1;
    private static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    private static final int DEFAULT_QUALITY = 3;
    private static final int UNEXPIRED_SELLIN = 15;

    @Test
	public void unexpiredDefaultItem_qualityDecreasesByOne() {

        // setup
		GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, UNEXPIRED_SELLIN, DEFAULT_QUALITY);
        
        // invoke
		app.updateQuality();

        // verify
		Item expectedItem = new Item(DEFAULT_ITEM, UNEXPIRED_SELLIN - 1, DEFAULT_QUALITY - 1);

        assertItem(expectedItem, app.items[0]);
	}

    
	@Test
	public void expiredDefaultItem_qualityDecreasesByTwo() {

        // setup
		GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELLIN, DEFAULT_QUALITY);
        
        // invoke
		app.updateQuality();
        
        // verify
        Item expectedItem = new Item(DEFAULT_ITEM, EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 2);
		
        assertItem(expectedItem, app.items[0]);
	}
    
    private void assertItem(Item expectedItem, Item actualItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn, actualItem.sellIn);
        assertEquals(expectedItem.quality, actualItem.quality);
    }
    
    private GildedRose createGildedRoseWithOneItem(String itemType, int sellin, int quality) {
        Item item = new Item(itemType, sellin, quality);
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        return app;
    }
}
