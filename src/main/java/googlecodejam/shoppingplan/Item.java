package googlecodejam.shoppingplan;

import java.util.Objects;

public class Item {
  
  private final String name;
  private final boolean perishable;
  
  public Item(String name, boolean isPerishable) {
    super();
    this.name = Objects.requireNonNull(name);
    this.perishable = isPerishable;
  }
  
  public String getName() {
    return name;
  }
  
  public boolean isPerishable() {
    return perishable;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 17;
    result = prime * result + ((name == null) ? 0
                                              : name.hashCode());
    result = prime * result + (perishable ? 1
                                          : 0);
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Item)) {
      return false;
    }
    Item other = (Item) obj;
    if (!name.equals(other.name)) {
      return false;
    }
    return perishable == other.perishable;
  }
  
}