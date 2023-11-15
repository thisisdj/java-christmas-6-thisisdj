package christmas.domain;

public enum Badge {

    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000);

    private final String name;
    private final int standardAmount;

    Badge(String name, int standardAmount) {
        this.name = name;
        this.standardAmount = standardAmount;
    }

    public String getName() {
        return name;
    }

    public int getStandardAmount() {
        return standardAmount;
    }
}
