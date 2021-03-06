package googlecodejam.shoppingplan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import googlecodejam.GoogleCodeJamSolver;

/**
 * A {@code ShoppingPlanCodeJamSolver} solves the "Shopping Plan" Code Jam.
 * <p>
 * <a href="https://code.google.com/codejam/contest/32003/dashboard#s=p3">Source </a>
 * <p>
 * The Code Jam is as follows:
 * <p>
 * You have a list of items you need to buy today, and you know the locations (represented as points
 * on a cartesian grid) of a few stores in the area. You also know which of these stores are selling
 * each item on your list, and at what price each store sells it. Given the price of gas, what is
 * the minimum amount you need to spend in order to buy all the items on your shopping list and then
 * drive back home? You start and end the journey at your house, which is located at (0,0).
 * <p>
 * To make matters interesting, some of the items on your list may be perishable. Whenever you make
 * a purchase that includes one or more perishable items, you cannot drive to another store without
 * first stopping back at your house. Every item on your shopping list is guaranteed to be sold by
 * at least one store, so the trip will always be possible.
 * <p>
 * Input
 * <p>
 * The first line of input gives the number of cases, <b>N</b>. <b>N</b> test cases follow. Each
 * case starts with a line formatted as
 * <p>
 * {@code num_items num_stores price_of_gas}
 * <p>
 * The next line contains the <b>num_items</b> items on your shopping list. The items will be space
 * separated, and each item will consist of only lowercase letters. If an item is perishable, its
 * name will be followed by a single exclamation point. There will be no duplicate items on your
 * list. The next <b>num_stores</b> lines will each be formatted as
 * <p>
 * {@code x_pos y_pos item1:price1 item2:price2 ...}
 * <p>
 * Each of these lines gives the location of one store, along with the items available at that store
 * and their corresponding prices. Only items which are on your shopping list will appear in these
 * lists. Perishable items will not end with exclamation points on these lists. No item will be
 * repeated in a store's list. Each store will offer at least one item for sale. No two stores will
 * be at the same location, and no store will be located at (0,0).
 * <p>
 * Output
 * <p>
 * For each test case, output one line containing "Case #<b>x</b>: " followed by the minimum
 * possible cost of the trip, rounded to seven decimal places. Don't forget about
 * <b>price_of_gas</b>, which is the amount of money you must spend per unit distance that you
 * drive.
 * <p>
 * Limits
 * <p>
 * 1 &le; <b>N</b> &le; 100,<br>
 * 0 &le; <b>price_of_gas</b> &le; 1000,<br>
 * -1000 &le; <b>x_pos</b> &le; 1000,<br>
 * -1000 &le; <b>y_pos</b> &le; 1000,<br>
 * 1 &le; price of each item &le; 1000.
 * <p>
 * Small dataset
 * <p>
 * 1 &le; <b>num_items</b> &le; 5,<br>
 * 1 &le; <b>num_stores</b> &le; 10.
 * <p>
 * Large dataset
 * <p>
 * 1 &le; <b>num_items</b> &le; 15,<br>
 * 1 &le; <b>num_stores</b> &le; 50.
 * <p>
 * Sample
 * <table summary="Example input and output">
 * <tr>
 * <th>Input
 * <th>Output
 * <tr>
 * <td>2
 * <td>Case #1: 400.0000000
 * <tr>
 * <td>1 2 10
 * <td>Case #2: 519.2920690
 * <tr>
 * <td>cookies
 * <tr>
 * <td>0 2 cookies:400
 * <tr>
 * <td>4 0 cookies:320
 * <tr>
 * <td>3 3 5
 * <tr>
 * <td>cookies milk! cereal
 * <tr>
 * <td>0 2 cookies:360 cereal:110
 * <tr>
 * <td>4 0 cereal:90 milk:150
 * <tr>
 * <td>-3 -3 milk:200 cookies:200
 * </table>
 */
public class ShoppingPlanCodeJamSolver extends GoogleCodeJamSolver {
  
  /**
   * Solves a single test case of the Shopping Plan Google Code Jam.
   */
  @Override
  protected String solve(Scanner in) throws IOException {
    String firstLine = in.nextLine();
    String[] results = firstLine.split(" ");
    int numItems = Integer.parseInt(results[0]);
    int numStores = Integer.parseInt(results[1]);
    int priceOfGas = Integer.parseInt(results[2]);
    
    Map<String, Item> items = readInItems(in, numItems);
    
    List<Store> stores = readInStores(in, numStores, items);
    
    double result = 1234567890.123456789;// TODO solve
    return String.format("%.7f", result); // Could also use %g
  }
  
  private Map<String, Item> readInItems(Scanner in, int numItems) {
    String itemsLine = in.nextLine();
    String[] itemsArray = itemsLine.split(" ");
    Map<String, Item> items = new HashMap<>(numItems);
    for (String item : itemsArray) {
      boolean perishable = false;
      if (item.contains("!")) { // ! will only be at the end of an item
        item = item.substring(0, item.length() - 1);
        perishable = true;
      }
      items.put(item, new Item(item, perishable));
    }
    
    return items;
  }
  
  private List<Store> readInStores(Scanner in, int numStores, Map<String, Item> items) {
    List<Store> stores = new ArrayList<>(numStores);
    for (int i = 0; i < numStores; i++) {
      String storeLine = in.nextLine();
      String[] storeAttributes = storeLine.split(" ");
      int x = Integer.parseInt(storeAttributes[0]);
      int y = Integer.parseInt(storeAttributes[1]);
      Store store = new Store(x, y);
      for (int index = 2; index < storeAttributes.length; index++) {
        String[] itemAttributes = storeAttributes[index].split(":");
        Item item = items.get(itemAttributes[0]);
        Integer price = Integer.parseInt(itemAttributes[1]);
        store.addItem(item, price);
      }
    }
    
    return stores;
  }
  
  /**
   * Runs the {@code ShoppingPlanCodeJamSolver} application.
   * 
   * @param args Can optionally contain a file name. If none is provided, this application reads its
   *          input from {@code System.in}.
   * @throws IOException If an I/O error occurs.
   */
  public static void main(String[] args) throws IOException {
    new ShoppingPlanCodeJamSolver().go(args);
  }
  
}