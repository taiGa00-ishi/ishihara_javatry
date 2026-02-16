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

import org.docksidestage.bizfw.basic.objanimal.barking.IBreatheInAction;

/**
 * The object for zombie(ゾンビ).
 * @author jflute
 * @author taiGa00-ishi
 */
public class Zombie extends Animal implements IBreatheInAction {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final ZombieDiary zombieDiary = new ZombieDiary();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
    }
    
    // #1on1: 具象to具象のオブジェクトの例 (2026/02/16)
    //@Override
    //protected BarkingProcess createBarkingProcess() {
    //    return new ZombieBarkingProcess(this, () -> downHitPoint());
    //}
    //
    //public class ZombieBarkingProcess extends BarkingProcess {
    //
    //    public ZombieBarkingProcess(Animal animal, IDownHitPoint downHitPoint) {
    //        super(animal, downHitPoint);
    //    }
    //
    //    @Override
    //    protected void breatheIn() {
    //        super.breatheIn();
    //        zombieDiary.countBreatheIn();
    //    }
    //}
    
    // #1on1: @Transactionalの処理の挟み込みの仕組みの話 (2026/02/16)
    
    // #1on1: JavaとKotlinで、オーバーライド可否のデフォルトが違う話 (2026/02/16)
    // jfluteのC#での体験。

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    public static class ZombieDiary {

        private int breatheInCount;

        public void countBreatheIn() {
            ++breatheInCount;
        }

        public int getBreatheInCount() {
            return breatheInCount;
        }
    }
    
    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    // done ishihara 修行++: Zombieの挙動をキープするように by jflute (2025/10/20)
    // done ishihara 修行#: 経由がpublicになっているのでdownHitPoint()と同じ問題 by jflute (2025/11/04)
    // ただ、解決方法は別にもあって、downHitPoint()の方と同じやり方じゃなくてもいい。
    // hint1: オブジェクト指向はもっと自由 (石原さんが最初思いついていたやり方(路線)で頑張ってみましょう)
    // hint2: step6の範疇で実現可能
    // #1on1: 上の方で「具象to具象のオブジェクトの例」のお話した // (2026/02/16)
    @Override
    public void breatheInAction() {
        zombieDiary.countBreatheIn();
    }

    @Override
    public String getBarkWord() {
        return "uooo"; // what in English?
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    public void downHitPoint() {
        // do nothing, infinity hit point
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ZombieDiary getZombieDiary() {
        return zombieDiary;
    }
}
