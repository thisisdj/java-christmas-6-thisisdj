package christmas.domain;

public enum Gift {

    Champagne("샴페인", 120_000, Menu.Champagne.getPrice(), 1);

    private final String name;
    private final int standardAmount;
    private final int price;
    private final int giftNum;

    Gift(String name, int standardAmount, int price, int giftNum) {
        this.name = name;
        this.standardAmount = standardAmount;
        this.price = price;
        this.giftNum = giftNum;
    }

    public int getStandardAmount() {
        return standardAmount;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getGiftNum() {
        return giftNum;
    }
}
