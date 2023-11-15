package christmas.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Discounter {

    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;

    private static final int DEFAULT_DDAY_DISCOUNT = 1_000;
    private static final int ADDITIONAL_DDAY_DISCOUNT = 100;
    private static final int MIN_DDAY_DAY = 1;
    private static final int MAX_DDAY_DAY = 25;

    private static final int WEEKDAY_DISCOUNT_UNIT = 2_023;
    private static final int WEEKEND_DISCOUNT_UNIT = 2_023;

    private static final List<Integer> SPECIAL_DAY = Arrays.asList(3, 10, 17, 24, 25, 31);
    private static final int SPECIAL_DAY_DISCOUNT = 1_000;

    public static int getDdayDiscount(int day) {
        if (day >= MIN_DDAY_DAY && day <= MAX_DDAY_DAY) {
            return (day - MIN_DDAY_DAY) * ADDITIONAL_DDAY_DISCOUNT + DEFAULT_DDAY_DISCOUNT;
        }

        return 0;
    }

    public static int getWeekDayDiscount(Map<Menu, Integer> bill, int day) {
        int today = getDayOfWeek(day);
        if (today == 5 || today == 6) {
            return 0;
        }

        return getTotalMenuCount(bill, MenuType.DESSERT) * WEEKDAY_DISCOUNT_UNIT;
    }

    public static int getWeekendDiscount(Map<Menu, Integer> bill, int day) {
        int today = getDayOfWeek(day);
        if (today <= 4 || today >= 7) {
            return 0;
        }

        return getTotalMenuCount(bill, MenuType.MAIN) * WEEKEND_DISCOUNT_UNIT;
    }

    public static int getGiftDiscount(List<Gift> gifts, int totalPrice) {
        return gifts
                .stream()
                .filter(gift -> totalPrice >= gift.getStandardAmount())
                .mapToInt(Gift::getPrice)
                .sum();
    }

    public static int getSpecialDiscount(int day) {
        if (SPECIAL_DAY.contains(day)) {
            return SPECIAL_DAY_DISCOUNT;
        }

        return 0;
    }

    private static int getTotalMenuCount(Map<Menu, Integer> bill, MenuType menuType) {
        int orderCount = 0;
        for (Menu menu : Menu.values()) {
            if (menu.getType() == menuType) {
                orderCount += bill.get(menu);
            }
        }

        return orderCount;
    }

    public static int getDayOfWeek(int day) {
        LocalDate localDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, day);
        return localDate.getDayOfWeek().getValue();
    }
}
