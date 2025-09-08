package org.docksidestage.bizfw.basic.buyticket;

// #1on1: enumという文法の仕組みについて (2025/09/08)
// チケット種別という概念を個数が限定されたインスタンスで表現している。
// 結局は、(少なくともJavaでは)ある意味単なるクラス。

// TODO ishihara javadocお願いしますm(_ _)m by jflute (2025/09/08)

public enum TicketType {
    ONE_DAY,
    TWO_DAY,
    FOUR_DAY,
    NIGHT_ONLY_TWO_DAY
}
