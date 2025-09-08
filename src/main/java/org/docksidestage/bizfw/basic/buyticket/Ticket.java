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
 * @author jflute
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // TODO ishihara [いいね] 定義位置がわかりやすい。contructorの引数の順序と同じで... by jflute (2025/08/25)
    // 固定値と動的に変わる値で区別されているのでGood。
    private final int displayPrice;
    private final int validDays;
    private final boolean nightOnly;
    private final TicketType ticketType;
    private int usedDays;
    private boolean alreadyIn;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
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
    public int getDisplayPrice() {
        return displayPrice;
    }

    public int getValidDays() {
        return validDays;
    }

    public boolean isNightOnly() {
        return nightOnly;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public int getUsedDays() {
        return usedDays;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

}
