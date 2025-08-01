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

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

// done ishihara javadocのauthor by jflute (2025/07/14)
/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taiGa00-ishi
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
        // seaが904より大きければifの条件に当てはまるが、904より大きくないのでelseの条件が適用され7となる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
        // 上から順に条件分岐
        // 先ほどと同様にsea>904は弾かれる。
        // その次の条件文では904以上の時と書かれている
        // 以上の時はその値も含むのでここの条件に入る
        // このタイミングで他の条件に入ることはなくなるので7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) {
            sea = 7;
        } else if (sea >= 903 || land) {
            sea = 8;
            if (!land) {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10
        // 1個目の大きな条件分岐のブロックではelse if (sea >= 903 || land)の条件に入る
        // その後seaは8になりlandはfalseつまり!landであるので、その条件文に入りland = trueになる
        // この1個目の大きなifからelseまでのブロックを抜けた後に新たなifに入る。
        // or文の条件でありパイプラインの前の条件は満たしていないが、後ろ側の条件は満たしている
        // なのでdecrementされる
        // 最後のif文では(land)つまりlandがtrueの時に入る。
        // ここでseaが10に変更されるので出力は10となる

        // done ishihara [いいね] "1個目の大きな条件分岐のブロック" という言葉が、全体の構造がちゃんと頭に入ってる証拠ですね by jflute (2025/07/10)
        
        // done jflute #1on1 にて、ソースコードリーディングのよもやま話をする予定 (2025/07/10)
        // 漠然読み(構造を把握) => フォーカス読み(知りたいところピンポイントで)
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside
    }
    // prepareStageList()では４つの文字列を配列に格納している、格納されているもののインデックスは0から始まり3まで
    // そのため、iが1の時は2番目の要素であるdocksideが取得され、seaに格納される

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
    }
    // このfor文ではstageListの要素の範囲が抜けるまで回り続ける繰り返し処理だと考えた。
    // 一回のループで配列の先頭から順番に代入される
    // 最後に代入されるmagiclampのみがseaに格納されていると考えた。
    // #1on1: 普通のfor文とは？

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hanger
    }
    // 最初のif文のbrで始まるものがあればcontinueはあまりこの中のロジックでは意味のないイメージ
    // このループが終わるタイミングをみるとgaが文字列に含まれていた時に終わるのでその時に格納されているhangerが答えになると考えた。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside
    }
    // sbはインスタンスが作られて何も操作されていないので、基本的にはlengthは0。
    // ただforループでstageがdocksideの時にsbにappend（追加）される。
    // そのためsbの長さは0より大きくなりreturnされる。
    // そのsbをseaに代入するので出力はdocksideであると考えた。
    
    // #1on1: forEach()はただのメソッドであって文法ではない。 (2025/07/28)
    // 中で文法としてのfor文を代理しているだけ。
    // コンピューター的にはループなのかどうか？って厳密にはわかってない。
    // なので、continueもbreakも使えない。メソッドなのでreturnは使える。
    
    // #1on1: forEach()制約多いけど何が良い？ => クローズドだから安全な面もあるかも？ by ishiharaさん
    // 外側の世界に影響を与えにくい方が、安心/安全という考え方にもなる。(できない方が安全)
    // 実際の現場では、わりと素直に回すループの方が圧倒的に多いので、continue,breakでこねくり回せないループが向いてる。
    // 副作用という言葉。

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        List<String> result = new ArrayList<>();
        List<String> stageList = prepareStageList();

        stageList.forEach(stage ->
        {
            if (stage.contains("a")){
                result.add(stage);
            }
        });

        for (String element: result){
            log(element);
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        String sea;
        // TODO ishihara StringBuilderの変数は、sbみたいに付けることが多いです by jflute (2025/07/28)
        // str だと、本当にただの String なのかな？という風に見えちゃう。(これはJavaの世界の感覚なので最初は知らなくて当然)
        StringBuilder str = new StringBuilder();
//        for (String stage : stageList) {
//            if (stage.startsWith("br")) {
//                continue;
//            }
//            sea = stage;
//            if (stage.contains("ga")) {
//                break;
//            }
//        }
        stageList.forEach(stage ->{
            if (stage.startsWith("br")) {
                return; // continue
            }
            if (stage.contains("ga")) {
                 str.append(stage);
                return; // break
            }
        });
        sea = str.toString();
        log(sea); // should be same as before-fix
    }
    // continueに置き換わるとこもそもそもいらない気がする（次のlistの要素にいくだけ）
    // #1on1: 確かに、もう後続の処理がないので、gaの方のreturnはしなくても大丈夫かも。
    // brの方のreturnは、例えば "brga" というような文字列の場合にskipさせないといけないので必要。
    // TODO ishihara 修行++: もし、"hangar" が stageList の中に存在しない場合、結果が同じになるでしょうか？ by jflute (2025/07/28)
    // また、hangar の後に bongar という別の文字列が存在したときに、同じ結果になるでしょうか？
    // そういった stageList の内容が変わるケースでも、結果が同じになるようにしてみましょう。

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 以下のコードからログには何が出力されるでしょうか？
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(5);
        numbers.add(20);
        numbers.add(15);
        numbers.add(30);

        String result = "";
        int sum = 0;

        for (int i = 0; i < numbers.size(); i++) {
            int currentNum = numbers.get(i);

            if (currentNum % 2 == 0) {
                if (currentNum > 15) {
                    sum += currentNum;
                    result = "Big Even";
                } else {
                    result = "Small Even";
                }
            } else {
                if (currentNum < 10) {
                    result = "Small Odd";
                } else {
                    result = "Big Odd";
                }
            }

            if (sum > 20) {
                break;
            }
        }
        log(result + ":" + sum);
    }
    // done ishihara [いいね] ふはー、合ってた、良かったー。難しい良いエクササイズですね(^^ by jflute (2025/07/28)

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
