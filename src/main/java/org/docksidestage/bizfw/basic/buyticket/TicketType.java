package org.docksidestage.bizfw.basic.buyticket;

// #1on1: enumという文法の仕組みについて (2025/09/08)
// チケット種別という概念を個数が限定されたインスタンスで表現している。
// 結局は、(少なくともJavaでは)ある意味単なるクラス。

// done ishihara javadocお願いしますm(_ _)m by jflute (2025/09/08)
/**
 * 利用可能なチケットの種類を表す列挙型です。
 * <p>
 * この列挙型は、1日券、複数日券、夜間限定券などの特定のチケットオプションを定義します。
 * </p>
 * @author taiGa00-ishi
 */
public enum TicketType {
    /** 1日券です。 */
    ONE_DAY,
    /** 2日券です。 */
    TWO_DAY,
    /** 4日券です。 */
    FOUR_DAY,
    /** 夜間限定の2日券です。 */
    NIGHT_ONLY_TWO_DAY
}
