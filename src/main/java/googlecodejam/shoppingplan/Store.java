package googlecodejam.shoppingplan;

import java.util.HashMap;
import java.util.Map;

public class Store {
  
  private final int x;
  private final int y;
  private final Map<Item, Integer> itemsPriceMap;
  
  public Store(int x, int y) {
    this.x = x;
    this.y = y;
    itemsPriceMap = new HashMap<>();
  }
  
  public void addItem(Item item, Integer price) {
    itemsPriceMap.put(item, price);
  }
  
  public int getPrice(Item item) {
    return itemsPriceMap.get(item);
  }
  
  public Map<Item, Integer> getPriceMap() {
    return new HashMap<>(itemsPriceMap);
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 17;
    result = prime * result + ((itemsPriceMap == null) ? 0
                                                       : itemsPriceMap.hashCode());
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Store)) {
      return false;
    }
    Store other = (Store) obj;
    if (x != other.x) {
      return false;
    }
    if (y != other.y) {
      return false;
    }
    if (itemsPriceMap == null) {
      if (other.itemsPriceMap != null) {
        return false;
      }
    } else if (!itemsPriceMap.equals(other.itemsPriceMap)) {
      return false;
    }
    return true;
  }
  
  @Override
  public String toString() {
    return "Store (" + x + ", " + y + ", itemPrices=" + itemsPriceMap;
    
  }
}