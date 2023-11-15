package christmas.domain;

public enum DiscountType {

    D_DAY_DISCOUNT("크리스마스 디데이 할인: ", true),
    WEEKDAY_DISCOUNT("평일 할인: ", true),
    WEEKEND_DISCOUNT("주말 할인: ", true),
    SPECIAL_DISCOUNT("특별 할인: ", true),
    GIFT_EVENT("증정 이벤트: ", false);

    private final String name;
    private final boolean isAffectPrice;

    DiscountType(String name, boolean isAffectPrice) {
        this.name = name;
        this.isAffectPrice = isAffectPrice;
    }

    public String getName() {
        return name;
    }

    public boolean isAffectPrice() {
        return isAffectPrice;
    }
}
