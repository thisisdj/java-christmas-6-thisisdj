package christmas.domain;

public enum Menu {
    // 에피타이저
    MushroomSoup("양송이수프", 6_000, MenuType.APPETIZER),
    Tapas("타파스", 5_500, MenuType.APPETIZER),
    CaesarSalad("시저샐러드", 8_000, MenuType.APPETIZER),

    // 메인
    T_BornStake("티본스테이크", 55_000, MenuType.MAIN),
    BBQRib("바비큐립", 54_000, MenuType.MAIN),
    SeafoodPasta("해산물파스타", 35_000, MenuType.MAIN),
    XMasPasta("크리스마스파스타", 25_000, MenuType.MAIN),

    // 디저트
    ChocoCake("초코케이크", 15_000, MenuType.DESSERT),
    IceCream("아이스크림", 5_000, MenuType.DESSERT),

    // 음료
    ZeroCola("제로콜라", 3_000, MenuType.DRINK),
    RedWine("레드와인", 60_000, MenuType.DRINK),
    Champagne("샴페인", 25_000, MenuType.DRINK),
    ;

    private final String name;
    private final int price;
    private final MenuType type;

    private static final String MENU_ERROR = "[ERROR] 해당 메뉴를 찾을 수 없습니다.";

    Menu(String name, int price, MenuType type) {
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

    public MenuType getType() {
        return type;
    }

    public static Menu findMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }

        throw new IllegalArgumentException(MENU_ERROR);
    }
}
