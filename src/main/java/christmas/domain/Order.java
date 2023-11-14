package christmas.domain;

import christmas.Message;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> bill = new EnumMap<>(Menu.class);

    public Order(String[] orders) {
        for (Menu menu : Menu.values()) {
            bill.put(menu, 0);
        }

        addOrders(orders);
    }

    public Map<Menu, Integer> getBill() {
        return new EnumMap<>(bill);
    }

    private void addOrders(String[] orders) {
        int totalOrderQuantity = 0, drinkOrderQuantity = 0;

        for (String order : orders) {
            String[] orderDetail = order.split("-");
            int quantity = Integer.parseInt(orderDetail[1]);
            Menu userPickMenu = getMenu(orderDetail[0]);

            validOverlapOrder(userPickMenu);

            // 음료 주문 저장
            if (userPickMenu.getType() == MenuType.DRINK) {
                drinkOrderQuantity += quantity;
            }

            totalOrderQuantity += quantity;
            bill.put(userPickMenu, bill.get(userPickMenu) + quantity);
        }

        validOrderQuantity(totalOrderQuantity, drinkOrderQuantity);
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (Menu menu : bill.keySet()) {
            totalPrice += menu.getPrice() * bill.get(menu);
        }

        return totalPrice;
    }

    private Menu getMenu(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu;
            }
        }

        throw new IllegalArgumentException(Message.NOT_VALID_ORDER.getMessage());
    }

    public void validOverlapOrder(Menu userPickMenu) {
        int defQuantity = bill.get(userPickMenu);

        // 중복된 주문
        if (defQuantity > 0) {
            throw new IllegalArgumentException(Message.NOT_VALID_ORDER.getMessage());
        }
    }

    public void validOrderQuantity(int totalOrderQuantity, int drinkOrderQuantity) {
        // 총 주문이 20개를 초과하거나 음료만 주문한 경우
        if (totalOrderQuantity > 20 || totalOrderQuantity == drinkOrderQuantity) {
            throw new IllegalArgumentException(Message.NOT_VALID_ORDER.getMessage());
        }
    }
}
