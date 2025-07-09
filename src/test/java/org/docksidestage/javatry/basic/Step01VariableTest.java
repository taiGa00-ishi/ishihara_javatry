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

import java.math.BigDecimal;
import java.util.Locale;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8:mai
        // 実際はmystic8null:mai
        // nullは何もないことを定義しているからここでnullを格納した変数を代入してもコンソールには何も現れないと思っていた
        // 文字列結合をする処理の際にnullは"null"の文字列として扱われる。
        // 同様にint型があっても代入先のseaがstring型なので、文字列結合の処理となり8も"8"として扱われる
        // JVM系の言語はこの道理が通じそう
        // C駆動のもの（python,Go）とかはシンプルにエラー。型変換してあげないといけない
        // コメント前に半角スペースを空けるのが一般的そう
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        land = land + "'s dreams";
        log(sea); // your answer? => oneman
        // seaにlandの"onman"を代入後、landに新たに文字列連携しているが、その変更後のものをseaに再代入していない
        // なのでonemanだけしか出力されないと考えた。

    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
        // 先ほどの問題と同様に、seaに代入した後にlandをincrementしてもseaの値には影響がない
        // 値渡しではなく参照渡しであれば、共通のアドレスを見ているので、landの変更に影響されると思う。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        log(sea); // your answer? => 417
        // 実際は416
        // seaに415を代入して、そこから2回addされているので417だと考えていた。
        // 最後のsea.add(new BigDecimal(1)はその結果を代入する先がなかったので加算されず、416という結果が出たと考える。
        // ちなみにBigDecimalを使うと計算の誤差を発生させずに十進数での計算ができるよう
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => null
        // instanceの初期化がされた後に、なにも代入されていないので何もないを意味するnullであろうと推測した。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => 0
        // 先ほどの問いと同様に初期化されただけのインスタンスを代入しても何もない。int型の何もない時は0で返ってくると考えた。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => 0
        // 実際はnullが出た
        // Integerは参照型で、プリミティブ型ではないので何もない時はnullを持つ？
        // intだとプリミティブ型なのでnullを持つことができない -> 0になる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband|1|null|burn
        // helpInstanceメソッドの中でそれぞれのインスタンスに対して、代入、incrementされていてその結果が反映されて出力されると考えた。
        // instanceHangerは何も代入されていないのでnullのまま
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor416
        // シンプルに文字列連結が行われてharbor416になると考えていた。
        // 実際はharbor
        // Stringは特殊な参照型でimmutableであるため、一度宣言されると変更不可能。新しいStringが必要
        // なのでconcatしたときに何も代入先がないので意味はなかった。
        // intはそもそもプリミティブ型なので値渡しでただ関数に数値のコピーを渡しているだけでメソッドないでどういじっても反映されない
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor416
        // appendではバッファに追加していく認識をしていたので、先ほどのconcatと違いharborの後に416が追加されると考えた
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor
        // メソッドないのseaにはharbor416が入っているが、
        // そのseaはhelpMethodArgumentVariable内で作られたローカル変数と扱われると考えた
        // sea = new と新しいのが作られているところからわかる
        // よってこのメソッド内では受け取ったseaに対しては何もしていないと考えた。
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        int piari = 0;
        sea = sea + "," + land + "," + piari;
        log(sea);
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 以下のJavaコードを読み、log() メソッドが最終的に何を出力するかを予測してください
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        // write your code here
        String name = "Taiga";
        StringBuilder greeting = new StringBuilder("Hello, ");
        int age = 25;

        processData(name, greeting, age);

        log(greeting);
    }

    private void processData(String name, StringBuilder greeting, int age){
        name = name.toUpperCase();
        greeting.append("I'm ").append(name);
        age++;
        String tmp = greeting.toString();
        greeting = new StringBuilder(tmp).append("I'm turning into ").append(age).append(" next year.");
    }
}
