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

import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO done ishihara 修正入れたら、ぜひauthor追加を (他のクラスも) by jflute (2025/10/06)
/**
 * The object for dog(犬).
 * @author jflute
 * @author taiGa00-ishi
 */
public class Dog extends Animal implements FastRunner {


    private static final Logger logger = LoggerFactory.getLogger(Dog.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Dog() {
    }

    // TODO done ishihara メソッド定義位置、Constructor配下は変なので、Runnerのタグコメント作って配置しましょう (他のクラスも) by jflute (2025/10/06)
    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public String getBarkWord() {
        return "wan"; // bow? in English
    }
    // ===================================================================================
    //                                                                              Runner
    //                                                                              ======
    @Override
    public void run() {
        logger.debug("...Running now");
        downHitPoint();
    }
}
