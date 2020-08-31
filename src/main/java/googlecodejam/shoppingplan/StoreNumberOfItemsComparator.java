package googlecodejam.shoppingplan;

import java.util.Comparator;

public class StoreNumberOfItemsComparator implements Comparator<Store> {

	@Override
	public int compare(Store store1, Store store2) {
		int store1NumItems = store1.getPriceMap().size();
		int store2NumItems = store2.getPriceMap().size();
		return Integer.compare(store1NumItems, store2NumItems);
  }
  
}