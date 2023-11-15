package christmas.domain;

import christmas.Message;

import java.util.*;

public class Order {

    private final Map<Menu, Integer> bill = new EnumMap<>(Menu.class);
    private final Map<DiscountType, Integer> discounts = new EnumMap<>(DiscountType.class);
    private int totalPrice;
    private int totalDiscountPrice;
    private int totalRealDiscountPrice;

    public Order(String[] orders, int date) {
        for (Menu menu : Menu.values()) {
            bill.put(menu, 0);
        }

        addOrders(orders);
        setTotalPrice();

        discounts.put(DiscountType.D_DAY_DISCOUNT, Discounter.getDdayDiscount(date));
        discounts.put(DiscountType.WEEKDAY_DISCOUNT, Discounter.getWeekDayDiscount(bill, date));
        discounts.put(DiscountType.WEEKEND_DISCOUNT, Discounter.getWeekendDiscount(bill, date));
        discounts.put(DiscountType.SPECIAL_DISCOUNT, Discounter.getSpecialDiscount(date));
        discounts.put(DiscountType.GIFT_EVENT, Discounter.getGiftDiscount(findUserGift(), getTotalPrice()));

        setTotalDiscountPrice(discounts);
    }

    public Map<DiscountType, Integer> getDiscounts() {
        return new EnumMap<>(discounts);
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public int getTotalRealDiscountPrice() {
        return totalRealDiscountPrice;
    }

    public Map<Menu, Integer> getBill() {
        return new EnumMap<>(bill);
    }

    private void setTotalDiscountPrice(Map<DiscountType, Integer> discounts) {
        int totalDiscountPrice = 0, totalRealDiscountPrice = 0;
        for (DiscountType type : discounts.keySet()) {
            if (type.isAffectPrice()) {
                totalRealDiscountPrice += discounts.get(type);
            }
            totalDiscountPrice += discounts.get(type);
        }

        this.totalDiscountPrice = totalDiscountPrice;
        this.totalRealDiscountPrice = totalRealDiscountPrice;
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
        return totalPrice;
    }

    public void setTotalPrice() {
        int totalPrice = 0;

        for (Menu menu : bill.keySet()) {
            totalPrice += menu.getPrice() * bill.get(menu);
        }

        this.totalPrice = totalPrice;
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

    public List<Gift> findUserGift() {
        List<Gift> giftList = new ArrayList<>();
        for (Gift gift : Gift.values()) {
            if (totalPrice >= gift.getStandardAmount()) {
                giftList.add(gift);
            }
        }

        return giftList;
    }
}
