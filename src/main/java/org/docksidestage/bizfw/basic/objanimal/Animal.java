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
        this.barkingProcess = new BarkingProcess(this);
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
    // TODO ishihara 修行++: protectedに戻せるように頑張ってみましょう (packageは動かさず) by jflute (2025/10/20)
    public void downHitPoint() {
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
}
