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
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

/**
 * The object for animal(動物).
 * @author jflute
 * @author taiGa00-ishi
 */
public abstract class Animal implements Loudable {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint;// is HP
    private final BarkingProcess barkingProcess;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
        this.barkingProcess = new BarkingProcess(this, () -> Animal.this.downHitPoint());
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        return barkingProcess.bark();
    }

    public abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    // #1on1: protectedは、サブクラスに見せる、もしくは、同じパッケージに見せる
    // done ishihara 修行++: protectedに戻せるように頑張ってみましょう (packageは動かさず) by jflute (2025/10/20)
    // TODO Done ishihara 修行#: BarkingProcess以外の人が呼べちゃうので、public Hubも使わずに実現したいところ by jflute (2025/11/04)
    // (hint: 先のstepに進んで、何かピンと来たときに対応するでOK)
    protected void downHitPoint() {
        --hitPoint;
        if (hitPoint <= 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }
    
    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }

    // 他のパッケージとの経由地点
    // #1on1: DBFluteのCBのembedCondition()の例をよもやま話:
    //  SQLインジェクション対策:
    //  where dfloc.MEMBER_NAME like 'sea%' escape '|'
    //   ↓
    //  where dfloc.MEMBER_NAME like ? escape '|'
    //  バインド変数(SQLの変数) → 1個目の?は "sea%" ですよ、って渡す
    //
    // 非推奨を使って危ない機能であることを演出する例: cb.embedCondition(...);
    //
    // 質問: 埋め込みでそんなに差が出ることがあるのか？ by いしはらさん
    // 回答: とあるレアケースでは、10秒が1秒みたいなレベルになったことがある by jflute
    // 仕組み的には...実行計画で10秒掛かる選択肢と、1秒で済む選択肢があって、どっちを選ぶか？
    // 通常は、速い方を選んでくれるけど、超時々レアケースで、遅い方を選んじゃうことがある。
    // そして、実際の値が明示されていることで、データ分布の偏りなどを使って速い方が選べることがある(あった)。
    /**
     * BarkingProcess専用メソッド。by jflute
     * 他の人は絶対呼ばないでください。
     */
//    public void downHitPointHub(){
//        downHitPoint();
//    }
}
