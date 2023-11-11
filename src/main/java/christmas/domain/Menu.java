package christmas.domain;

public enum Menu {
    // 에피타이저
    MushroomSoup("양송이수프", 6_000, "에피타이저"),
    Tapas("타파스", 5_500, "에피타이저"),
    CaesarSalad("시저샐러드", 8_000, "에피타이저"),

    // 메인
    T_BornStake("티본스테이크", 55_000, "메인"),
    BBQRib("바비큐립", 54_000, "메인"),
    SeafoodPasta("해산물파스타", 35_000, "메인"),
    XMasPasta("크리스마스파스타", 25_000, "메인"),

    // 디저트
    ChocoCake("초코케이크", 15_000, "디저트"),
    IceCream("아이스크림", 5_000, "디저트"),

    // 음료
    ZeroCola("제로콜라", 3_000, "음료"),
    RedWine("레드와인", 60_000, "음료"),
    Champagne("샴페인", 25_000, "음료"),
    ;

    private final String name;
    private final int price;
    private final String type;

    Menu(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
