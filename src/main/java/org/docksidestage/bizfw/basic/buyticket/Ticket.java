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

import java.time.LocalDateTime;

/**
 * チケットの状態と振る舞いを管理するクラスです。
 * <p>
 * このクラスは、個々のチケットの種別、有効性、および利用状況を保持します。
 * 入園処理などの振る舞いを扱い、チケットのプロパティへのアクセスを提供します。
 * </p>
 * @author jflute
 * @author taiGa00-ishi
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done ishihara [いいね] 定義位置がわかりやすい。contructorの引数の順序と同じで... by jflute (2025/08/25)
    // 固定値と動的に変わる値で区別されているのでGood。
    /** チケットの表示価格です。 */
    private final int displayPrice;
    /** チケットの有効日数です。 */
    private final int validDays;
    /** 夜間限定のチケットであるかどうかを示します。 */
    private final boolean nightOnly;
    /** チケットの具体的な種類です。 */
    private final TicketType ticketType;
    /** チケットが使用された日数です。 */
    private int usedDays;
    /** 当日すでに入園済みであるかどうかを示します。 */
    private boolean alreadyIn;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * 新しいチケットインスタンスを構築します。
     * @param displayPrice チケットの表示価格。
     * @param validDays チケットの有効日数。
     * @param nightOnly 夜間限定のチケットであるか。
     * @param ticketType チケットの具体的な種類。
     */
    public Ticket(int displayPrice, int validDays, boolean nightOnly, TicketType ticketType) {
        this.displayPrice = displayPrice;
        this.validDays = validDays;
        this.nightOnly = nightOnly;
        this.ticketType = ticketType;
        this.usedDays = 0;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // #1on1: 引数isNight方式だと、ユーザーが自分で夜かどうかを決められてしまう。
    // なので内部で現在日時からisNightを導けると、完全に間違いを防ぐことはできる。
    // だがしかし、UnitTestに都合がめちゃ悪い。
    // [doing -> done: 独立] ishihara 修行++: UnitTestの都合の解決しつつ、内部で夜の判定は隠蔽したいところ by jflute (2025/08/25)
    // いったんまず内部で判定するプログラムにして、UnitTestの都合の解決を最後にって感じで段階踏んでOK。
    // とりあえずゲストからinputされないようにするのはmust
    // この関数の内部で時間を取得して、夜かどうかを判定する
    // UnitTestの場合は、別のテスト関数を用意して、夜の時間固定でテストする
    //
    // #1on1: 内部で現在日時を解決するファーストステップはOK。
    // 一方で、UnitTestで自在に昼/夜のテストをするというのが大変。
    //
    // TODO ishihara 修行#: UnitTestの都合の解決、昼でも夜でも自在にいつでもテストできるように by jflute (2025/09/08)
    // hint: とりあえずstep6をやってからでOKです。
    public void doInPark() {
        if (usedDays >= validDays && alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }

        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();

        if (this.nightOnly && (hour < 17 || hour > 23)) {
            throw new IllegalStateException("You cannot enter by night-only ticket in the daytime.");
        }
        alreadyIn = true;
        usedDays++;
    }

    // #1on1: これはこれで頑張って考えたのは素晴らしい (2025/09/08)
    // どうせやるなら、doDoInPark(int currentHour) でロジックは共有した方が良い。
    public void TestDoInPark(){
        if (usedDays >= validDays && alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }

        //夜の時間を固定する
        int hour = 17;

        if (this.nightOnly && (hour < 17 || hour > 23)) {
            throw new IllegalStateException("You cannot enter by night-only ticket in the daytime.");
        }
        alreadyIn = true;
        usedDays++;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // [done] ishihara どうせなら、getterもインスタンス変数の順序を合わせてもらえたらと by jflute (2025/08/25)
    // #1on1: @return必須/非必須話、getterで両方書くと冗長感話。説明を省略してもOK話。
    // (jflute的には、IN/OUTの重要で、結構説明抜きで@paramと@returnだけのjavadocもよく書いている)
    /**
     * チケットの表示価格を返します 
     */
    public int getDisplayPrice() {
        return displayPrice;
    }

    /** チケットの有効日数を返します */
    public int getValidDays() {
        return validDays;
    }

    /** 夜間限定のチケットであるかを返します */
    public boolean isNightOnly() {
        return nightOnly;
    }

    /** Enumのタイプをもとにチケットの具体的な種類を返します */
    public TicketType getTicketType() {
        return ticketType;
    }

    /** チケットが使用された日数を返します */
    public int getUsedDays() {
        return usedDays;
    }

    /** 当日すでに入園済みであるかを返します */
    public boolean isAlreadyIn() {
        return alreadyIn;
    }

}
