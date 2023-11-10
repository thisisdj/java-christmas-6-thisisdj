package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.Message;

import static christmas.Message.NOT_VALID_DATE;

public class InputView {
    private static final String GREET = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String DATE_CHECK = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_CHECK = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public int printGreeting() {
        System.out.println(GREET);
        System.out.println(DATE_CHECK);
        return toInteger(Console.readLine());
    }

    private int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.out.println(NOT_VALID_DATE.getMessage());
            throw new IllegalArgumentException();
        }
    }
}