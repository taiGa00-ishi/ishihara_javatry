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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taiGa00-ishi
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? => over
    }
    // supplySomething()メソッドの中でseaに"over"が代入されているので、log(sea)で"over"が出力される
    // supplySomething()メソッド自体が呼ばれた時はその関数内に設定されているログも出るin supply: {}の形で

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic");
        consumeSomething(supplySomething());
        runnableSomething();
        log(sea); // your answer? => mysmys
    }
    // functionSomething()メソッドの中で引数の"mystic"が"mys"に置き換えられ、returnされ、seaに入ってくる
    // consumeSomething()メソッドは引数の"over"が"mystic"に置き換えられ、ログに出力される
    // ただ、これは最後のseaの出力に影響はない
    // runnableSomething()メソッドは引数の"outofshadow"がログに出力されるだけで、seaの出力には影響しない

    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable);
        if (!land) {
            sea = sea + mutable.getStageName().length();
        }
        log(sea); // your answer? => 910
    }

    // int seaはプリミティブ型（Immutable）な値なのでhelloMutable()メソッド内での変更は影響しない
    // boolean landはプリミティブ型（Immutable）なので、helloMutable()メソッド内での変更は影響しない
    // St4MutableStage piariはオブジェクト型（Mutable）なので、helloMutable()メソッド内での変更は影響する
    // if文の中でlandがfalseなので、seaは904 + "mystic"の長さとなる
    // "mystic"の長さは6なので、最終的にseaは910となる

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;
        offAnnualPassport(hasAnnualPassport);
        for (int i = 0; i < 100; i++) {
            goToPark();
        }
        ++sea;
        sea = inParkCount;
        log(sea); // your answer? => 100
    }

    // hasAnnualPassportは本体のメソッドでtrueに設定される。
    // 引数として渡されるhasAnnualPassportはメソッド内で変更されるが、これはローカル変数であり、インスタンス変数には影響しない。
    // goToPark()メソッドではインスタンス変数を引数として使っておらず、直接値にアクセスするので、変更が反映される。
    // そのため、100回のループでinParkCountが100回増加し、最終的にseaは100となる。

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */

    private boolean availableLogging;

    public void test_method_making() {
        String replaced = replaceCwithB(replaceAwithB("ABC"));
        String sea = quote(replaced, "'");
        if (isAvailableLogging()) {
            showSea(sea);
        }
    }

    // done ishihara [いいね] privateメソッドの定義順、呼び出し順序に一致してて直感的にわかりやすい by jflute (2025/08/14)
    // #1on1: privateメソッドの定義順、jfluteの一例の紹介、何かしらカテゴリを意識してもらえると嬉しい。
    // write methods here
    private String replaceAwithB(String str){
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++){
            char c = str.charAt(i);
            if (c == 'A'){
                sb.append('B');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();

        // return str.replace("A", "B");
        // ライブラリ使用バージョン
    }

    private String replaceCwithB(String str){
        return str.replace("C", "B");
    }

    private String quote(String str, String quotation) {
        return quotation + str + quotation;
    }

    private boolean isAvailableLogging() {
        availableLogging = true;
        return availableLogging;
    }

    private void showSea(String sea) {
        log(sea);
    }
    
}
