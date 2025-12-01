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
package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author jflute
 * @author taiGa00-ishi
 */
public class St6MySql extends Database {

    // done ishihara 1: offsetの計算を再利用したいところ by jflute (2025/10/20)
    // done ishihara 2: 2stepから、3stepに変わった時 (真ん中に処理を追加...) by jflute (2025/10/20)
    // そういうときでも、1箇所直せば済むようにしたいところ。
    // (つまり現状は、流れが再利用できてないということ。"1" で個々の処理は再利用できたとしても...)
    
    // #1on1: 意図してなかった、イメージに引きづられてpublic by いしはらさん
    // オーバーライドメソッドは、可視性のreduceはできないけど、expandはできる。
    // 
    // Database mysql = ...
    // mysql.doBuildPagingQuery(pageSize, offset); // 呼べない
    // new St6MySql().doBuildPagingQuery(pageSize, offset); // 呼べる
    //
    // 抽象クラスと具象クラスの距離感、例えば、抽象クラスがフレームワーク提供だったり。
    // TODO ishihara publicにする必要がないので、superに合わせてprotectedに by jflute (2025/11/17)
    @Override
    protected String doBuildPagingQuery(int offset, int pageSize) {
        return "limit " + offset + ", " + pageSize;
    }
}
