package model;

public enum BankIds {
    MELI (603799),
    SEPAH(589210),
    SADERAT(603769),
    KESHAVARZI(603770),
    MASKAN(628023),
    EGHTESADENOVIN(627412),
    PARSIAN(622106),
    SARMAYE(639607),
    DEY(502938),
    MELAT(610433),
    TEJARAT(627353);

    private  int id;
    BankIds(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }
}
