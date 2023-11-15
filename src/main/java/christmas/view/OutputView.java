package christmas.view;

import christmas.domain.Badge;
import christmas.domain.DiscountType;
import christmas.domain.Menu;
import christmas.domain.Order;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String SALE_LIST_PREVIEW = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String ORDER_MENU_UNIT = "개";
    private static final String PRICE_UNIT = "원";
    private static final String ALERT_SALE_BEFORE_TOTAL_PRICE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU = "<증정 메뉴>";
    private static final String SALE_LIST = "<혜택 내역>";
    private static final String TOTAL_SALE_PRICE = "<총혜택 금액>";
    private static final String ALERT_SALE_AFTER_TOTAL_PRICE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE = "<12월 이벤트 배지>";
    private static final String NONE_MESSAGE = "없음\n";

    private static final DecimalFormat decFormat = new DecimalFormat("###,###");

    public void printOrderList(Order order) {
        System.out.println(SALE_LIST_PREVIEW);
        System.out.println(ORDER_MENU);

        for (Menu menu : order.getBill().keySet()) {
            int orderQuantity = order.getBill().get(menu);
            if (orderQuantity > 0) {
                System.out.println(menu.getName() + " " + orderQuantity + ORDER_MENU_UNIT);
            }
        }

        System.out.print("\n");
    }

    public void printOrderTotalPrice(Order order) {
        System.out.println(ALERT_SALE_BEFORE_TOTAL_PRICE);
        System.out.println(toCurrencyUnit(order.getTotalPrice()) + PRICE_UNIT + "\n");
    }

    public void printGift(Order order) {
        System.out.println(GIFT_MENU);
        StringBuilder sb = new StringBuilder();
        order.findUserGift().forEach((item) -> {
            sb.append(item.getName()).append(" ").append(item.getGiftNum()).append(ORDER_MENU_UNIT).append("\n");
        });

        if (sb.isEmpty()) {
            System.out.println(NONE_MESSAGE);
            return;
        }

        System.out.println(sb);
    }

    public void printDiscountList(Order order) {
        System.out.println(SALE_LIST);
        Map<DiscountType, Integer> discounts = order.getDiscounts();
        StringBuilder sb = new StringBuilder();
        for (DiscountType discountType : DiscountType.values()) {
            int discount = discounts.get(discountType);
            if (discount == 0) {
                continue;
            }

            sb.append(discountType.getName()).append("-").append(toCurrencyUnit(discount)).append(PRICE_UNIT).append("\n");
        }

        if (sb.isEmpty()) {
            System.out.println(NONE_MESSAGE);
            return;
        }

        System.out.println(sb);
    }

    public void printTotalDiscount(Order order) {
        System.out.println(TOTAL_SALE_PRICE);
        int discount = order.getTotalDiscountPrice();
        String message = toCurrencyUnit(discount) + PRICE_UNIT + "\n";

        if (discount > 0) {
            System.out.println("-" + message);
            return;
        }

        System.out.println(message);
    }

    public void printAfterDiscount(Order order) {
        System.out.println(ALERT_SALE_AFTER_TOTAL_PRICE);
        System.out.println(toCurrencyUnit(order.getTotalPrice() - order.getTotalRealDiscountPrice()) + PRICE_UNIT + "\n");
    }

    public void printEventBadge(Order order) {
        StringBuilder sb = new StringBuilder();
        for (Badge badge : Badge.values()) {
            if (order.getTotalDiscountPrice() >= badge.getStandardAmount()) {
                sb.append(badge.getName());
                break;
            }
        }

        if (sb.isEmpty()) {
            System.out.println(NONE_MESSAGE);
            return;
        }

        System.out.println(EVENT_BADGE);
        System.out.println(sb);
    }

    public String toCurrencyUnit(int totalPrice) {
        return decFormat.format(totalPrice);
    }
}
