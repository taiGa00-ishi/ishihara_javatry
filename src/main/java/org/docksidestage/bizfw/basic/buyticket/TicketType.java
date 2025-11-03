package org.docksidestage.bizfw.basic.buyticket;

// #1on1: enumという文法の仕組みについて (2025/09/08)
// チケット種別という概念を個数が限定されたインスタンスで表現している。
// 結局は、(少なくともJavaでは)ある意味単なるクラス。

// done ishihara javadocお願いしますm(_ _)m by jflute (2025/09/08)
/**
 * 利用可能なチケットの種類を表す列挙型です。
 * <p>
 * この列挙型は、1日券、複数日券、夜間限定券などの特定のチケットオプションを定義します。
 * 各チケットタイプには価格、有効日数、夜間限定フラグの情報が含まれています。
 * </p>
 * @author taiGa00-ishi
 */
public enum TicketType {
    /** 1日券です。 */
    ONE_DAY(7400, 1, false),
    /** 2日券です。 */
    TWO_DAY(13200, 2, false),
    /** 4日券です。 */
    FOUR_DAY(22400, 4, false),
    /** 夜間限定の2日券です。 */
    NIGHT_ONLY_TWO_DAY(7400, 2, true);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int price;
    private final int validDays;
    private final boolean nightOnly;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    TicketType(int price, int validDays, boolean nightOnly) {
        this.price = price;
        this.validDays = validDays;
        this.nightOnly = nightOnly;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * チケットの価格を返します。
     */
    public int getPrice() {
        return price;
    }

    /**
     * チケットの有効日数を返します。
     */
    public int getValidDays() {
        return validDays;
    }

    /**
     * 夜間限定チケットかどうかを返します。
     * @return 夜間限定チケットの場合true
     */
    public boolean isNightOnly() {
        return nightOnly;
    }
}
