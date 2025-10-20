/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * チケット売り場の情報を管理しています
 * <p>ここではチケットのそれぞれの値段、チケットの残り枚数、チケットの購入プロセスを管理しています。</p>
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** 最大のチェケット枚数です */
    private static final int MAX_QUANTITY = 10;
    /** onedayチケットの値段です */
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    /** twodayチケットの値段です */
    private static final int TWO_DAY_PRICE = 13200;
    /** fourdayチケットの値段です */
    private static final int FOUR_DAY_PRICE = 22400;
    /** nightonly twodayチケットの値段です */
    private static final int NIGHT_ONLY_TWO_DAY_PRICE = 7400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // [done] ishihara javadoc, returnも付けましょう。(書くからにはin/outはしっかり) by jflute (2025/08/25)
    // done ishihara @returnの書き方、型は書かなくてOK。説明だけでOK by jflute (2025/09/22)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return 返り値としてはTicketを返す
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        // [done] ishihara OneDayの戻り値を変更してResult統一にしちゃってもいいし... by jflute (2025/08/25)
        // 一方で、ほんのわずかな無駄処理は許容して、Result受け取ってgetTicket()でもいいし...
        // #1on1: その程度の問題のお話をさせて頂きました。(2025/08/25)
        //TicketBuyResult xxx = doBuyTicket(handedMoney, ONE_DAY_PRICE ,1, false);
        //return xxx.getTicket();
        // [done] ishihara 現状だと、doBuyTicket()がTicketを戻してるので、resultを用意する必要はない by jflute (2025/09/08)
        // doBuyTicket() の戻り値を Result にしてしまって...
        // お釣りの計算や、Resultの構築もprivateメソッドに入れて再利用してしまって...
        // ただ、OneDayではResultを受け取ってTicketだけ戻す、という方が良いかなと。
        TicketBuyResult result = doBuyTicket(handedMoney, ONE_DAY_PRICE, 1, false, TicketType.ONE_DAY);
        return result.getTicket();
    }

    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        // TODO ishihara 修行++: TWO(2)が多い、一個のTWOで表現できるようにしたい by jflute (2025/09/22)
        return doBuyTicket(handedMoney, TWO_DAY_PRICE, 2, false, TicketType.TWO_DAY);
    }
    
    // #1on1: 時間を置いた自己レビューをするといい (2025/08/14)
    // #1on1: コピペはできるだけ避ける一方で、コピペでも修正漏れを防ぐ手段は自分なり確立しておいたほうがいい (2025/08/14)
    // (jfluteの一例を紹介)
    // #1on1: IntelliJ上での冗長部分の指摘について。
    // #1on1: 在庫共有型？在庫分離型？どっちにするか話
    // この目線は業務でとても大事 by いしはらさん

    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        return doBuyTicket(handedMoney, FOUR_DAY_PRICE, 4, false, TicketType.FOUR_DAY);
    }
    
    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        return doBuyTicket(handedMoney, NIGHT_ONLY_TWO_DAY_PRICE, 2, true, TicketType.NIGHT_ONLY_TWO_DAY);
    }


    // done ishihara publicメソッドと実処理のprivateメソッドで先頭文字が同じだと補完時に区別つきづらい by jflute (2025/08/25)
    // e.g. doBuyTicket(), internalBuyTicket()
    // IntelliJでrename機能があるのでそれで直してみましょう。
    // rename後にoption + enter(return)で一括修正した
    private TicketBuyResult doBuyTicket(int handedMoney, int ticketPrice, int validDays, boolean nightOnly, TicketType ticketType) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < ticketPrice) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + ticketPrice;
        } else {
            salesProceeds = ticketPrice;
        }
        Ticket ticket = new Ticket(ticketPrice, validDays, nightOnly, ticketType);
        int change = handedMoney - ticketPrice;
        return new TicketBuyResult(ticket, change);
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========

    /** TwoDayPassportの値段を返します。 */
    public int getTwoDayPrice() {
        return TWO_DAY_PRICE;
    }

    /** チケットの残り枚数を返します。 */
    public int getQuantity() {
        return quantity;
    }

    /** 売上金額を返します。 */
    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
