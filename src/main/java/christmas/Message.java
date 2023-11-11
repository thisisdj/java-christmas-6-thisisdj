package christmas;

public enum Message {

    NOT_VALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_VALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ;

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
