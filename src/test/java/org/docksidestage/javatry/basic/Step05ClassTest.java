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
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 * 
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 * 
 * @author jflute
 * @author taiGa00-ishi
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? => 9
    }
    // 最初にTicketBoothのインスタンスが作成されたときにこのインスタンスが持っているプロパティは
    // quantityが10、salesProceedsがnull
    // buyOneDayPassportメソッドを呼び出した時にhandedMoneyに7400が渡される
    // その後、quantityが1減って9になり、salesProceedsに7400が入る
    // getQuantityメソッドを呼び出すと先ほど1枚減った9枚のquantityとしてseaに入る


    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 10000　→ 修正後　7400
    }
    // buyOneDayPassportメソッドを呼び出した時にhandedMoneyに10000が渡される
    // その後、quantityが1減って9になり、salesProceedsに10000(handedMoney)が入る
    // getSalesProceedsメソッドを呼び出すとsalesProceedsの値である10000がseaに入る

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => null
    }
    // TicketBoothのインスタンスが作成されたときにsalesProceedsはnullで初期化されている
    // 先ほどと違い、buyOneDayPassportメソッドを呼び出していない
    // そのため、getSalesProceedsメソッドを呼び出すとsalesProceedsの値であるnullがseaに入る

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? => 9　→ 修正後　10
    }
    // 色々エラーメッセージがまず出現する
    // なぜなら渡すお金が7400より小さく足りないのでExceptionが帰ってくる
    // doTest_class_ticket_wrongQuantityメソッドの中でもエラーをキャッチしたらメッセージを出すようにしている
    // しかしbuyOneDayPassportメソッドを呼び出した時にquantityが0でないかぎりは1減る
    // チケットを買っていなくても、quantityは1減る実装になっている

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getQuantity();
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
    }
    // quantityが減るタイミングをhandedMoneyとチケット代のチェックが終わったタイミングに変更

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
    }
    // salesProceedsに追加されていくものはチケット代そのものに切り替え（handedMoney → ONE_DAY_PRICE）

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        // uncomment after making the method
//        TicketBooth booth = new TicketBooth();
//        int money = 14000;
//        int change = booth.buyTwoDayPassport(money);
//        Integer sea = booth.getSalesProceeds() + change;
//        log(sea); // should be same as money
//
//        // and show two-day passport quantity here
//        log(booth.getQuantity());
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        // uncomment out after modifying the method
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark();
        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // uncomment after modifying the method
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();
        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
    }

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */
    public void test_class_moreFix_usePluralDays() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = buyResult.getTicket();
        log(twoDayPassport.getUsedDays());

        // 1日目
        twoDayPassport.doInPark();
        log(twoDayPassport.getUsedDays());

        // 2日目
        twoDayPassport.doInPark();
        log(twoDayPassport.getUsedDays());

        // 3日目
        try {
            twoDayPassport.doInPark();
        } catch (IllegalStateException e) {
            log(e.getMessage());
        }
    }

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statemet. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        // uncomment when you implement this exercise
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        showTicketIfNeeds(oneDayPassport);
        TicketBuyResult buyResult = booth.buyTwoDayPassport(100000);
        Ticket twoDayPassport = buyResult.getTicket();
        showTicketIfNeeds(twoDayPassport);
    }

    // uncomment when you implement this exercise
    private void showTicketIfNeeds(Ticket ticket) {
        // TODO [done] ishihara 修行++: nightOnlyのTwoDayが混じってしまう by jflute (2025/08/25)
        // "正確に" がポイント。今後、チケットの種別が色々な方向性で増えても判定ロジックが変わらないようにしたい
        // 同じdaysでも価格帯だけは全部異なるので、getTwoDayPriceを追加して、価格で判定した
        // もし同じ価格帯が出てきたらどうするか
        // enumでTypeを作って、Ticketに持たせるのが良いかも
        // 最初はこれで記述
        // if (ticket.getDisplayPrice() == booth.getTwoDayPrice())
        if (ticket.getTicketType() == TicketType.TWO_DAY) { // write determination for two-day passport
            log("two-day passport");
        } else {
            log("other");
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_four() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        int handedMoney = 50000;
        TicketBuyResult buyResult = booth.buyFourDayPassport(handedMoney);
        Ticket fourDayPassport = buyResult.getTicket();
        log(fourDayPassport.getUsedDays());

        // 1日目
        fourDayPassport.doInPark();
        log(fourDayPassport.getUsedDays());

        // 2日目
        fourDayPassport.doInPark();
        log(fourDayPassport.getUsedDays());

        // 3日目
        fourDayPassport.doInPark();
        log(fourDayPassport.getUsedDays());

        // 4日目
        fourDayPassport.doInPark();
        log(fourDayPassport.getUsedDays());

        // 5日目
        try {
            fourDayPassport.doInPark();
        } catch (IllegalStateException e) {
            log(e.getMessage());
        }

    }

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult buyResult = booth.buyNightOnlyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = buyResult.getTicket();
        log(twoDayPassport.getUsedDays());

        // 1日目
        twoDayPassport.doInPark();
        log(twoDayPassport.getUsedDays());

        // 2日目
        twoDayPassport.doInPark();
        log(twoDayPassport.getUsedDays());

        // 3日目
        try {
            twoDayPassport.doInPark();
        } catch (IllegalStateException e) {
            log(e.getMessage());
        }
    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        // your confirmation code here
    }

    // #1on1: Ticketクラスは多くの人が利用するクラスと想定して (2025/08/25)
    // 質問: 現場であんまりコメント見かけないけど、どういうルールなんだろう？
    // 回答: 恐らくルール自体がないだろう。コメントは義務にならないものなので。
    // ただ、コメントを上手に書ける人だからこそ、書かなくて良いかどうかの判断できる。
    // (あと、コメントは書かないと上手にならない。一回書きすぎるくらいの体験があると良い)
    // #1on1: 本気で書いた場合のサンプル NxBatchRecorder を紹介
    // あと、他社さんの例:
    /**
     * Write intelligent JavaDoc comments seriously on the public classes/constructors/methods of the Ticket class. <br>
     * (Ticketクラスのpublicなクラス/コンストラクター/メソッドに、気の利いたJavaDocコメントを本気で書いてみましょう)
     */
    public void test_class_moreFix_yourSuperJavaDoc() {
        // your confirmation code here
    }
}
