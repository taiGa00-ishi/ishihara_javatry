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

import org.docksidestage.bizfw.basic.supercar.SupercarClient;
import org.docksidestage.javatry.basic.st7.St7BasicExceptionThrower;
import org.docksidestage.javatry.basic.st7.St7ConstructorChallengeException;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author TaiGa00-ishi
 */
public class Step07ExceptionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_basic_catchfinally() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        StringBuilder sea = new StringBuilder();
        try {
            thrower.land();
            sea.append("dockside");
        } catch (IllegalStateException e) {
            sea.append("hangar");
        } finally {
            sea.append("broadway");
        }
        log(sea); // your answer? => hangarbroadway
    }
    // tryでthrowerのlandを呼び出している
    // landメソッドから繋がっているメソッドを追っていくと、onemanメソッドでIllegalStateExceptionがthrowされている
    // そのため、catchに辿り着いて、seaに"hangar"が追加される
    // その後、finallyにきて、seaに"broadway"が追加される

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_basic_message() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        String sea = null;
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? =>oneman at showbase
    }
    // seaに入るものを見るとexceptionのメッセージが入ることがわかる
    // IllegalStateExceptionがthrowされる時のメッセージを見ると"oneman at showbase"となっている


    /**
     * What class name and method name and row number cause the exception? (you can execute and watch logs) <br>
     * (例外が発生したクラス名とメソッド名と行番号は？(実行してログを見て良い))
     */
    public void test_exception_basic_stacktrace() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            log(e);
        }
        // your answer? => St7BasicExceptionThrowerクラスのonemanメソッドの40行目
        // スタックトレースの１番上に来ているのが原因出るため
    }

    // done jflute 次回1on1, エラーと例外の違い、NullPo, チェック例外 (2025/12/01)
    // ===================================================================================
    //                                                                           Hierarchy
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_hierarchy_Runtime_instanceof_RuntimeException() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof RuntimeException;
        log(sea); // your answer? => ture
    }

    // IllegalStateExceptionのもとを辿っていくと、extends RuntimeExceptionとなっていたので、
    // expはRuntimeExceptionのインスタンスであると言えるのでtrueが入る

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Exception() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => true
    }
    // IllegalStateException -> RuntimeException -> Exception -> Throwable <- implements Serializableとなっている
    // そのため、expはExceptionのインスタンスであると言えるのでtrueが入る

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Error() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Error;
        log(sea); // your answer? => false
    }

    // 上記で関係を書いているが、Errorを継承している箇所がなかったのでfalseが入る

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Throwable() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Throwable;
        log(sea); // your answer? => true
    }
    // Exceptionが継承しているのがThrowableなので元を辿るとtrueが入る

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Throwable_instanceof_Exception() {
        Object exp = new Throwable("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => false
    }

    // ThrowableはExceptionを継承していない(あくまでも継承されている立場)のでExceptionのインスタンスの要素がないためfalseが入る
    /*
                           +-----------------+
                           |                 |
                  +------------------+       |
                  |     Throwable    | <>----+
                  +------------------+
                           △ 
                           ｜
            +------------------------------+
            |                              |
   +--------------------+        +-------------------+
   |       Error        |        |    Exception      |
   +--------------------+        +-------------------+
     NoSuchMethodError                    △ 
     OutOfMemoryError                     ｜
                           +------------------------------+
                           |                              |
                 +--------------------+        +-------------------+
                 |  RuntimeException  |        |    XxxException   |
                 +--------------------+        +-------------------+
                           △                 IOException, SQLException
                           ｜
                   NullPointerException            
                   IllegalStateException
     */
    // #1on1: throwする瞬間にエラー扱いできるもの、できない (2026/01/05)
    // 例外は、catchされるまでは「(システム)エラー扱い」か「正常なレアケース」かは、
    // 厳密にはわからないでthrowされている感覚。

    // ===================================================================================
    //                                                                         NullPointer
    //                                                                         ===========
    /**
     * What variable (is null) causes the NullPointerException? And what row number? (you can execute and watch logs) <br>
     * (NullPointerが発生する変数(nullだった変数)と、発生する行番号は？(実行してログを見ても良い))
     */
    public void test_exception_nullpointer_basic() {
        try {
            String sea = "mystic";
            String land = sea.equals("mystic") ? null : "oneman";
            String lowerCase = land.toLowerCase();
            log(lowerCase);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => 5162710183389028792L
        // 実際はnullだったのはland変数で、発生した行番号は151行目(String lowerCase = land.toLowerCase();)
        // landに入るのがnullなのでlowercaseにするときにNullPointerExceptionが発生する
        // 早とちりしてNullPointerExceptionのIDを書いてしまった
        // 問題をちゃんと見れていなかった
        // エラーメッセージを見せるなら、lowerCaseのThrow new NullPointerExceptionしているところにメッセージを入れれば良い？
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_nullpointer_headache() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int sum = land.length() + piari.length();
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => 問題となった変数はpiariで行数は170行目(int sum = land.length() + piari.length();)
        // length()の呼び出し先に行くとNullPointerExceptionがはっきり書かれていないがString.javaの最初の注意書きに以下のように書かれていたので、
        // 基本的にnullを渡すとNullPointerExceptionが発生することがわかる
        // * <p> Unless otherwise noted, passing a <tt>null</tt> argument to a constructor
        // * or method in this class will cause a {@link NullPointerException} to be
        // * thrown.
    }

    /**
     * Refactor to immediately understand what variable (is null) causes the NullPointerException by row number in stack trace. <br>
     * (どの変数がNullPointerを引き起こしたのか(nullだったのか)、スタックトレースの行番号だけでわかるようにリファクタリングしましょう)
     */
    public void test_exception_nullpointer_refactorCode() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int landLength = land.length();
            int piariLength = piari.length();
            int sum = landLength + piariLength;
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
    }

    // ===================================================================================
    //                                                                   Checked Exception
    //                                                                   =================
    /**
     * Show canonical path of new java.io.File(".") by log(), and if I/O error, show message and stack-trace instead <br>
     * (new java.io.File(".") の canonical path を取得してログに表示、I/Oエラーの時はメッセージとスタックトレースを代わりに表示)
     */
    public void test_exception_checkedException_basic() {
        // 1,canonical pathの取得 <- 現在のパスから絶対パスに正規変換
        // 2,ログに表示
        // 3,IOExceptionが発生したら、メッセージとスタック
        try{
            java.io.File currentPath = new java.io.File(".");
            String canonicalPath = currentPath.getCanonicalPath();
            log(canonicalPath);
        } catch (java.io.IOException e) {
            log("I/O error has occurred.: " + e.getMessage(), e);
        }
        // #1on1: チェック例外とは？チェック例外流行ってない？話 (2026/01/05)
        // もう別にいいでしょって場面でも、わりとthrows宣言されていて呼び出し側が面倒なことが多い。
        // さらにLambda式と相性が悪い。
        // (ちなみに、Kotlinにはチェック例外はない。引き継がなかったってこと)
    }

    // ===================================================================================
    //                                                                               Cause
    //                                                                               =====
    /**
     * What string is sea variable in the catch block?
     * And What is exception class name displayed at the last "Caused By:" of stack trace? <br>
     * (catchブロックの変数 sea, land の中身は？また、スタックトレースの最後の "Caused By:" で表示されている例外クラス名は？)
     */
    public void test_exception_cause_basic() {
        String sea = "mystic";
        String land = "oneman";
        try {
            throwCauseFirstLevel();
            fail("always exception but none");
        } catch (IllegalStateException e) { // StateException
            Throwable cause = e.getCause(); // firstのcauseつまりsecondの例外 => IllegalArgument
            sea = cause.getMessage(); // IllegalArgumentのメッセージ
            land = cause.getClass().getSimpleName(); // IllegalArgumentのクラス名
            log(sea); // your answer? => Failed to call the third help method: symbol=-1
            log(land); // your answer? => IllegalArgumentException
            log(e); // your answer? =>  eは何を指している? <- stacktrace?
            // caused byの最後に表示されているのはNumberFormatException
        }
    }
    // まずFirstLevelでMAX - 0x7ffffffeをsymbolに入れている
    // 0x7ffffffeは2147483646,maxは2147483647,symbolには1が入る
    // secondで-2をしてsymbol=-1となるのでif文の中に入りThirdLevelに行く
    // ThirdLevelでsymbolが負の数なのでInteger.valueOf("piari")が呼ばれる
    // ここでValueOfのNumberFormatExceptionが発生し、thirdLevelの呼び出しもとのsecondでエラーがキャッチされる
    // NumberFormatExceptionをキャッチしたのでIllegalArgumentExceptionがthrowされる
    // eは何を指している? <- stacktrace?、そのExceptionのインスタンス？
    // ホバーしてみるとそれぞれの例外クラスのインスタンスであることがわかる
    // それだったらlandってなんでIllegalArgumentExceptionになるの？だろう
    // それぞれのレベルでeに違う例外インスタンスを宣言(catch)しているのにNumberFormatExceptionをキープできていのはなぜ？
    //
    // #1on1: 具体的に追っていった。(2025/12/01)
    // 文法的な例外の階層構造について説明、それがなぜ必要なのか？は次のエクササイズにて。
    private void throwCauseFirstLevel() {
        int symbol = Integer.MAX_VALUE - 0x7ffffffe;
        try {
            throwCauseSecondLevel(symbol);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to call the second help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseSecondLevel(int symbol) {
        try {
            --symbol;
            symbol--;
            if (symbol < 0) {
                throwCauseThirdLevel(symbol);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to call the third help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseThirdLevel(int symbol) {
        if (symbol < 0) {
            Integer.valueOf("piari");
        }
    }

    // ===================================================================================
    //                                                                         Translation
    //                                                                         ===========
    /**
     * Execute this test and read exception message and write situation and cause on comment for non-programmer. <br>
     * テストを実行して例外メッセージを読んで、プログラマーでない人にもわかるように状況と原因をコメント上に書きましょう。
     */
    public void test_exception_translation_debugChallenge() {
        try {
            new SupercarClient().buySupercar();
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // What happens? Write situation and cause here. (何が起きた？状況と原因をここに書いてみましょう)
            // - - - - - - - - - -
            // 原因はハンドルの製造に必要な特殊なネジの製造がもうできないから今回のように車ができるまでに問題が発生した。
            // 大まかな流れとしては、まずクライアントが買いたい車の要望をディーラーに注文して、ディーラーがスーパーカーの製造業者にクライアントからの要望を元にカタログを指定します
            // そして、スーパーカーの製造業者はそのカタログに必要なハンドルをハンドルの製造業者に注文します
            // ハンドル業者はその注文に従って製造しようとします。その時にそのハンドルに必要なスペックのクリンチャー(\(^_^)/)を持つネジの製造業者に注文します
            // しかし、そのネジの製造業者はその特殊なネジをもう製造できないため、スーパーカーの製造に問題が発生します。
            // 製造における例外のメッセージはThe kawaii face is already unsupported so we cannot make it.
            //　\(^_^)/のクリンチャーはもうサポートされていないみたいである。
            // _/_/_/_/_/_/_/_/_/_/
        }
    }

    /**
     * Improve exception handling in supercar's classes to understand the situation
     * by only exception information as far as possible. <br>
     * できるだけ例外情報だけでその状況が理解できるように、Supercarのクラスたちの例外ハンドリングを改善しましょう。
     */
    public void test_exception_translation_improveChallenge() {
        try {
            new SupercarClient().buySupercar(); // you can fix the classes
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
        }
        
        // #1on1: 例外の翻訳Good (2025/12/24)
        // 例外の翻訳の実装もとても良い。
        //
        // RuntimeException統一、いったんそのようにしてみたという話。
        // 専用の例外クラスを作る方が、タイトル的な役割になって丁寧だけど...必須ではない。
        // 現場では、間の子な感じで、自分たちがthrowした例外であることだけを判別できるようにってのが多い。
        //  e.g. throw new [アプリ名]SystemException("Failed to make steering wheel for steeringWheelId: " + steeringWheelId, e);
        // フレームワークがthrowする例外の場合は、費用対効果が高いのでわりかしきっちりやることが多い。
        // (LastaFluteの例ちょこっと)
        /*
        try {
            TicketBuyResult buyResult = booth.buyFourDayPassport(handedMoney);
        } catch (TicketSoldOutException e) { // ifの代わり
            // 何かしらリカバリ処理を行う
        }
         */
        // バグだとしても固有例外を投げるか？
        // 厳密にはthrowする側(する瞬間)は、それがバグなのか？業務的なものか？はわからないので、
        // 面倒さをignoreして考えると、厳密には固有例外を投げたほうがベターではある。
        // (フレームワークはわりと業務から距離が遠いので、確定できないことが多いのでそうすることが多い)
        /*
                 catch        catch        catch
     <---- 例外  /      例外  /     例外    /    例外
             \ /         \ /          \ /       |
   o          |           |            |         \
  /|\   ->    A     ->    B    ->     C      ->      D -> D'
  /\           |     |        PK           ^^v
               |     |                     も
               +設定ファイル (PK)
         */
        // throwする側は自分のレイヤーで知ってる事実だけしか載せれないので、
        // だからこそ例外の翻訳をする必要もある。
        //
        // あと、エラーメッセージを正しく読むため (読もうと思ってもらうために) に、
        // 例外の翻訳のコンセプトを知ってもらうという狙い。
        //
        // ↓ちょっとこの話も:
        //
        // エラーメッセージ読め読め大合唱
        // https://jflute.hatenadiary.jp/entry/20130522/errorsinging
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Fix terrible (YABAI in Japanese) exception handling. (you can modify exception class) <br>
     * (やばい例外ハンドリングがあるので修正しましょう (例外クラスを修正してOK))
     */
    public void test_exception_writing_constructorChallenge() {
        try {
            helpSurprisedYabaiCatch();
        } catch (St7ConstructorChallengeException e) {
            log("Thrown by help method", e); // should show also "Caused-by" information
        }
    }

    private void helpSurprisedYabaiCatch() {
        try {
            helpThrowIllegalState();
        } catch (IllegalStateException e) {
            // #1on1: 元々は例外の翻訳ではなく上書き、してしまうとCaused byが全部消えてしまう。 (2026/01/05)
            throw new St7ConstructorChallengeException("Failed to do something.", e);
        }
    }

    private void helpThrowIllegalState() {
        if (true) { // simulate something illegal
            String importantValue = "dummy"; // important to debug
            throw new IllegalStateException("something illegal: importantValue=" + importantValue);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What is the concept difference between Exception and Error? Write it on comment. <br>
     * (ExceptionとErrorのコンセプトの違いはなんでしょうか？コメント上に書きましょう)
     */
    public void test_exception_zone_differenceExceptionError() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // Write here. (ここに書いてみましょう)
        // - - - - - - - - - -
        // Exceptionはあらかじめ予測がつくような異常系の例外でErrorは予測がつかなくて原因の調査に時間を要して対処が困難である異常系の事象のこと
        //
        //
        // _/_/_/_/_/_/_/_/_/_/
        // #1on1: 例外のHierarchyのところでメモ書いている (2026/01/05)
    }
}
