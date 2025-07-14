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

import org.docksidestage.unit.PlainTestCase;

// done ishihara ↑import文で unused の警告が出ています。削除をお願いします。Localeどこかで補完した？(^^ by jflute (2025/07/10)
// #1on1: import文がソースコードリーディングの役に立つ (クラスの依存関係を示しているので)
// #1on1: IDEで自動で整えることもできる

// done ishihara 事務的なレビューで申し訳ないですが、javatryではjavadocのauthorをお願いしています by jflute (2025/07/10)
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taiGa00-ishi
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
        // done ishihara [いいね] 思考コメントありがとうございます！深く考えてくださってありがたいです by jflute (2025/07/10)
        // よく昔のインターネットサービスの画面とかは、「こんにちは、nullさん」とか表示されてることがありました(^^。
        // 最近では減りましたが、今でもメールでは null になっちゃってるもの大企業でも時々見かけます。
        // ちなみに、C#だと、nullを足しても空文字になります。
        // ただ、ログに出力するときとかは、nullって出たほうがわかりやすいのでデバッグには良かったりすることも。
        // 些細な違いで優劣はそこまでないのですが、言語によってこういう細かい挙動が変わったりするもので。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        land = land + "'s dreams";
        log(sea); // your answer? => oneman
        // seaにlandの"oneman"を代入後、landに新たに文字列連携しているが、その変更後のものをseaに再代入していない
        // なのでonemanだけしか出力されないと考えた。
        // done ishihara [いいね] 変数のインスタンスの違いよく理解されていますね by jflute (2025/07/10)
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
        // done ishihara [いいね] 後のエクササイズで出てきていますが、mutableな参照渡しなら色々変わりますよね^^ by jflute (2025/07/10)
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
        // done ishihara [ふぉろー] そう、add() が自分に足すのか？足した結果を戻すのか？ by jflute (2025/07/10)
        // 両方のパターンがあるので、わかってないと挙動が読めないんですよね。
        // 後の思考コメントで登場していますが、Immutable なクラスなのかどうか？これで変わってきます。
        // IDE上で BigDecimal にカーソルを当てて、JavaDocコメントを表示してみてください。(ツールチップ)
        // 一言目に、immutable で書いてあります。それだけ重要な情報ということですね。
        
        // done jflute 1on1にて、immutableの追加の補足話をする予定 (2025/07/10)
        // (↑このtodoは、ぼくのtodoなので、そのまま残しておいてください。1on1のときに話したらdoneします)
        // #1on1: JavaDocのお話
        // #1on1: IntelliJでcontrol+Jのお話
        // #1on1: add()のソースコードリーディング
        // #1on1: immutable/mutableのバランス話
        // #1on1: kotlinでのval/varの話 (変数のimmutable)
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
        // done ishihara [ふぉろー] ここはややこしいですよね。呼び出し側の引数指定の変数と、メソッド引数名が同じですが... by jflute (2025/07/10)
        // 「引数指定の変数」と「メソッド引数」は、たまたま名前が同じだけの別の変数(入れ物)なんですね。
        // instanceMagiclamp = "burn"; は、スコープの短い引数(変数)に対して、元々の "magician" へのアドレス参照を破棄して、
        // "burn" へのアドレス参照に差し替えて、その次の行にはメソッドが終わってその変数は破棄、ということで。
        // helpメソッド内では、インスタンス変数のinstanceMagiclampは一切登場していないわけですね。
        // #1on1: 変数とインスタンスの関係性のお話
        // #1on1: インスタンス変数とIDE上のハイライトのお話
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
        // intはそもそもプリミティブ型なので値渡しでA関数に数値のコピーを渡しているだけでメソッドないでどういじっても反映されない
        // done ishihara [いいね] そう、String も immutable なので、インスタンス自体は変わらないわけですね。 by jflute (2025/07/10)
        // immutable は戻り値を無視すると何も起きないってのが注意ではあります。
        // concat() にカーソルを当てて JavaDoc を読むと「戻す」ってことがわかります。
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
        // done ishihara [いいね] ここは色々と罠が多いですが、StringBuilderインスタンスがもう一つ作られてるところがポイント。 by jflute (2025/07/10)
        // 二個のStringBuilderインスタンス (2インスタンス) がどこにいるか？ってのが読み解けるかどうかのエクササイズでした。
        // 極論、プログラムを見て、そこに変数が何個あって、インスタンスが何個あるのか？それらがどうつながってるのか？
        // これがイメージできるようになって欲しいという思いがあります。
        // けっこう曖昧でもお仕事の実装はできなくはないのですが、ここぞってときにミスって本番トラブルっての見ることあるので。
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
    private int piari = 0;

    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        // done ishihara piariはインスタンス変数と要件に書いてあるので、メソッドの外側に宣言しましょう by jflute (2025/07/10)
        // (エクササイズなので、宣言場所はこのメソッドの近くでOKです)

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

        // done ishihara [ざつだん] やってみた by jflute (2025/07/10)
        log(greeting); // your answer? => Hello, I'm TAIGA (o)
        // ドキドキした...1つ目のStringBuilderインスタンス、どこにも冒険に出てないよなぁって探して、
        // processメソッドでは途中で切り離されていて、後半は2つ目のStringBuilderしかいじってないと。
        // greeting変数は単なる引数の変数なので、1つ目インスタンスへの参照が2つ目のインスタンスへの参照に差し替わっただけ。
        // 意外に盲点になりそうなのが、さりげなく1行目でtoUpperCase()してるところ。元のStringインスタンスは変わらないが、
        // 引数変数のnameの参照先を大文字のものに差し替えてからappendしているので表示は大文字。
        // あー、良かったーーー。ぼくがこれで間違ったらやばいので笑
        //
        // で、ぼく自身がちょっとやってみて思ったのですが、ぼくはやはりインスタンス中心に見てる感覚ありますね。
        // 変数は単なるコントローラーみたいな感じで、参照のための名前付きリモコン。
        // よく新人のjavatryフォローしてると、「greetingインスタンス」みたいな感覚になってしまう方がわりといて、
        // 変数とインスタンスを合体させて認識しちゃってたりすると、複雑になったときによくわからなくなっちゃうみたいな。
        //
        // まあ、だからこそ immutable ってのはその複雑性が減らす手法の一つってところがあるのかもしれません。
        // (生成されるインスタンスの数は増えますけど、中間成果物インスタンスなのでそこまで気にしなくてもいいので)
        
        // #1on1: プログラムの自己レビューの濃度、重要なのか？普通なのか？話
    }

    private void processData(String name, StringBuilder greeting, int age){
        name = name.toUpperCase();
        greeting.append("I'm ").append(name);
        age++;
        String tmp = greeting.toString();
        greeting = new StringBuilder(tmp).append("I'm turning into ").append(age).append(" next year.");
    }
}
