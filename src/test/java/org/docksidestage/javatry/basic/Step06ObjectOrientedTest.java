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

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.objanimal.Zombie;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.bizfw.basic.objanimal.sleeper.Koala;
import org.docksidestage.bizfw.basic.objanimal.sleeper.LongSleeper;
import org.docksidestage.javatry.basic.st6.dbms.Database;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.os.Mac;
import org.docksidestage.javatry.basic.st6.os.OldWindows;
import org.docksidestage.javatry.basic.st6.os.St6OperationSystem;
import org.docksidestage.javatry.basic.st6.os.Windows;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author TaiGa00-ishi
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes (except simulation) in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、(simulationを除いて)間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() {
        // [done] ishihara あと3箇所あります (このテスト実行して動作を確認してもOK) by jflute (2025/09/08)
        //
        // [ticket booth info]
        //
        // simulation: actually these variables should be more wide scope
        int oneDayPrice = 7400;
        int quantity = 10;
        Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // simulation: actually this money should be from customer
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }

        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        // quantityの減るタイミングをこちらに変更
        --quantity;
        // 売り上げに加算されるのは買った金額なのでhandedMoneyではなくoneDayPrice
        salesProceeds = oneDayPrice;

        //
        // [ticket info]
        //
        // simulation: actually these variables should be more wide scope
        // displayPriceはquantityではなくoneDayPrice
        //int displayPrice = quantity;
        int displayPrice = oneDayPrice;
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        // simulation: actually this process should be called by other trigger
        if (alreadyIn) {
            // 代入された変数を使用していなかったのでquantityをdisplayPriceに変更
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        }
        alreadyIn = true;

        //
        // [final process]
        //
        // #1on1: int, int, intって引数は危ないので、メソッド作る側としては極力避ける努力する。
        // 1: データをできるだけオブジェクトで取り扱う
        // 2: Value Objectを導入する
        // 3: 引数の順序を工夫する (ただこれは完璧ではない)
        // 4: JavaDocしっかり書いて、引数を目立たせる (チリも積もれば策)
        //
        // 呼び出す側の意識:
        // 1: 指差し確認
        // 2: ここぞの嗅覚
        // → 集中力のコントロール
        //

        // 渡す引数の順番が間違っていた(salseProceedsとdisplayPrice)
        saveBuyingHistory(quantity, salesProceeds, displayPrice, alreadyIn);
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
        if (alreadyIn) {
            // simulation: only logging here (normally e.g. DB insert)
            showTicketBooth(quantity, salesProceeds);
            showYourTicket(displayPrice, alreadyIn);
            // saveなのにshowだけなのは何でやと思ったら、普通はDB insertすると但し書きがあった
            // 引数が逆になっていた(quantity <-> displayPrice)ので修正
        }
    }

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
        // 少し小言を言うと、Your Ticketのステータスとして値段よりもoneDayなのかどうかの方が重要な気がする
        // #1on1: いいね
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        // done ishihara コメントアウトされているのを戻して(復元)欲しい by jflute (2025/09/22)
        TicketBooth booth = new TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // if step05 has been finished, you can use this code by jflute (2019/06/15)
        Ticket ticket = booth.buyOneDayPassport(10000);


        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        ticket.doInPark();

        // *doInPark() has this process:
        //if (alreadyIn) {
        //     throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket);
    }

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getDisplayPrice(), ticket.isAlreadyIn());
    }

    // write your memo here:
    // 一個一個変数や関数の動きを見なくても、関数やインスタンスを使うだけで直感的にコードを追える、読みやすい。
    // それぞれのインスタンスが持っている変数や役割があり、ロジカルに役割の区分ができている。
    // 変数や関数のスコープが狭くなり、バグが起きにくい。
    // やっぱりdoShowYourTicket()がalreadyInを示すのはわかるが、displayPriceを示すのは違和感がある。(チケット種別を置換するか、付け足すのが良いきがする)
    // しかし買った経歴を保存するなら、displayPriceは必要な情報ではある気がする。お客さんに見せるためのdoShowYourTicket出ないのなら必要な気がする
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // what is object?
    // オブジェクトはそれぞれがプロパティや振る舞いを持っていて、責務が明確である。
    // それらのオブジェクトを組み合わせてシステムを構築することで、コードの可読性や保守性が向上する。
    // _/_/_/_/_/_/_/_/_/_/
    // #1on1: オブジェクトとは？を問う理由の話。

    // done jflute 次回1on1ふぉろーここから (2025/09/22)
    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    // #1on1: 多態性、多相性
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = dog.getHitPoint();
        log(land); // your answer? => null
    }
    // DogクラスにはgetBarkメソッドがありwanを返す
    // その帰ってきた値をsoundに代入し、getBarkWord()メソッドを呼び出しているので、wanが返ってくる
    // Dogクラス自体にはgetHitPoint()メソッドがなかったので何も帰ってこないと思っていた。
    // Dogクラスの宣言のところにextends Animalとあり、AnimalクラスにgetHitPoint()メソッドがあるので
    // そのメソッドが呼び出され、AnimalクラスのhitPoint変数が返ってくる

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        // #1on1: いぬはどうぶつである: is-aの関係
        // いぬは目覚まし時計である => まあ基本的には変 (比喩表現かな？)
        // どうぶつはいぬである => とは言い切れない
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }
    // Animal型のanimal変数にDogクラスのインスタンスを代入している
    // そのため、bark()メソッドを呼び出すとDogクラスのbark()メソッドが呼び出され、wanが返ってくる
    // getHitPoint()メソッドもAnimalクラスのメソッドが呼び出され、AnimalクラスのhitPoint変数が返ってくる
    // BarkedSoundの型でsound変数にanimal.bark()で代入をした時にanimal内のbarkメソッドでは3回downHitPoint()が呼ばれている
    // そのため、AnimalクラスのコンストラクタでhitPointが10に初期化されているので、10-3で7が返ってくる

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        // #1on1: test_メソッドの、Dogへの直接依存がなくなった
        // DogがCatに変わっても、test_メソッドは1文字も修正しなくていい
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }
    // createAnyAnimalで生成しているインスタンスはDogクラスのインスタンスなので先ほどと同じ結果になる

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
    }

    // #1on1: doAnimal...()もDog/Catへの依存なく、Animalだったら何でも受け付ける
    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }
    // doAnimalSeaLand_4thメソッドの中では先ほどまでと同じことをしている。そこにdogのインスタンスが渡されているので結果は同じものになる

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5th_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        log(land); // your answer? => 5
    }
    // 今回はAnimal型のcatのインスタンスを生成している
    // downHitPointのメソッドはcatのクラスでoverrideされている
    // cat内のdownHitPointメソッドでは、hitPointを1減らし、hitPointが偶数の時にさらに1減らす処理がされている
    // bark()メソッド内でdownHitPoint()が3回呼ばれて、9->7->5となる

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => vooo
        int land = animal.getHitPoint();
        log(land); // your answer? => -1
    }

    // Zombieクラスではhitpointが-1で初期化されている
    // オーバーライドされているdownHitPoint()では何も処理されていないので、hitPointは変化しない
    /**
     * What is happy if you can assign Dog or Cat instance to Animal variable? <br>
     * (Animal型の変数に、DogやCatなどのインスタンスを代入できると何が嬉しいのでしょう？)
     */
    public void test_objectOriented_polymorphism_7th_whatishappy() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is happy?
        // 共通のインターフェースを持つことで、異なるクラスのオブジェクトを同じ方法で扱うことができる。
        // それぞれのクラスに特有の仕様を柔軟的に記述することができる。共通のAnimal型の変数を使うコードを記述できる。
        // 共通のAnimal型のクラスにある関数を使用することができる。
        // _/_/_/_/_/_/_/_/_/_/
        // #1on1: 日常でのポリモーフィズム話 (2025/10/06)
    }

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => vooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? => vooo
    }
    // Loudable型のloudable変数にZombieクラスのインスタンスを代入している
    // LoudableはインターフェースでsoundLoudly()メソッドを持っている
    // このインターフェースはAnimalクラスにimplementされている
    // そのため、loudable.soundLoudly()メソッドを呼び出すとAnimalクラスのsoundLoudly()メソッドが呼び出される
    // そのメソッドではbarkメソッドとgetBarkWord()メソッドがチェーンされているので、voooをゲットして返している

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri---
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false
    }

    // 今回のLoudableのインターフェースを使っているのはAlarmClockクラスのインスタンス生成する時
    // AlarmClockクラスはAnimalクラスを継承していないので、loudable instanceof Animalはfalseになる

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
    }

    // CatクラスはFastRunnerインターフェースを実装しているのでtrue
    // ZombieクラスはFastRunnerインターフェースを実装していないのでfalse
    // そもそもZombieはあまり速く走るイメージがない
    /**
     * Make Dog class implement FastRunner interface. (the method implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (メソッドの実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        // your confirmation code here
        FastRunner runner = new Dog();
        runner.run();
    }

    /**
     * What is difference as concept between abstract class and interface? <br>
     * (抽象クラスとインターフェースの概念的な違いはなんでしょう？)
     */
    public void test_objectOriented_polymorphism_interface_whatisdifference() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is difference?
        // 抽象クラスは「is-a」の関係で振る舞いだけでなく状態も共有できる
        // インターフェースは「can-do」の関係で、振る舞いの定義をしている。
        // 抽象クラスは単一継承であるが、インターフェースは多重継承が可能である。 // Javaの文法的な話
        // _/_/_/_/_/_/_/_/_/_/
        // #1on1: "状態も共有できる"、"振る舞いの定義" あたり重要なポイント。
        // Javaは多重継承できない。できなくした。(C++, Rubyは多重継承ができる)
        // 代わりに interface。
        // 名前の付け方も、オブジェクト中心なのか？操作中心なのか？ (コンセプトが違うから)
        
        // TODO jflute 次回、特殊なinterfaceの使われ方の補足も e.g. ColorBox (2025/10/06)
        // フレームワークとか、ライブラリでのinterfaceの使われ方なども。
        // interfaceだと、実装が全然再利用できなくて、まだメリットがピンポイントが来ない by いしはらさん
        // (再利用の方法の話も)
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        // your confirmation code here
        Animal aussieAnimal = new Koala();
        log(aussieAnimal.getHitPoint());
        log(aussieAnimal.bark());
        log(aussieAnimal.getHitPoint());

    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        // your confirmation code here
        LongSleeper aussieAnimal = new Koala();
        aussieAnimal.sleep();
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // your confirmation code here
        Database mysql = new St6MySql();
        Database postgre = new St6PostgreSql();
        String mysqlQuery = mysql.buildPagingQuery(10, 3);
        String postgreQuery = postgre.buildPagingQuery(10, 3);
        log(mysqlQuery);
        log(postgreQuery);
        // done ishihara 飛んでる by jflute (2025/10/06)
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here
        St6OperationSystem mac = new Mac("taiga");
        String macPath = mac.buildUserResourcePath("Desktop/memo.txt");
        log(macPath);

        // Windows のコンクリートクラス
        St6OperationSystem windows = new Windows("TaiGa");
        String winPath = windows.buildUserResourcePath("Documents/report.docx");
        log(winPath);

        // Old Windows のコンクリートクラス
        St6OperationSystem oldWin = new OldWindows("oldTaiga");
        String oldWinPath = oldWin.buildUserResourcePath("My Documents/photo.jpg");
        log(oldWinPath);
        
        // done jflute MacとWindowsの再利用思考トレーニング (2025/10/06)
        // #1on1: "/Users/" + loginId は再利用する？しない？
        // 「再利用というのは、コードが同じだから再利用するのではなく、意味が同じだから再利用する」
        // (けっこうときどきたまたま同じなだけどコードというのもあるので注意)
        // メソッドに切り出すことを想像して、名前に困るかどうか？が一つの判断基準。
        // (DB設計のテーブル名でのエピソード by いしはらさん)
        //
        // TODO jflute WindowsとOldWindowsの再利用思考トレーニング (2025/10/06)
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // your confirmation code here
        Animal test1 = new Dog();
        Animal test2 = new Cat();
        Animal test3 = new Koala();
        log(test1.bark());
        log(test2.bark());
        log(test3.bark());
    }

    /**
     * Put barking-related classes, such as BarkingProcess and BarkedSound, into sub-package. <br>
     * (BarkingProcessやBarkedSoundなど、barking関連のクラスをサブパッケージにまとめましょう)
     * <pre>
     * e.g.
     *  objanimal
     *   |-barking
     *   |  |-BarkedSound.java
     *   |  |-BarkingProcess.java
     *   |-loud
     *   |-runner
     *   |-Animal.java
     *   |-Cat.java
     *   |-Dog.java
     *   |-...
     * </pre>
     */
    public void test_objectOriented_writing_withPackageRefactoring() {
        // your confirmation code here
        Animal test1 = new Dog();
        Animal test2 = new Cat();
        Animal test3 = new Koala();
        log(test1.bark());
        log(test2.bark());
        log(test3.bark());
    }

    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it corrent?
        // 不適切
        // ゾンビだけdownHitPoint()メソッドで何もしないことをしている。
        // 抽象クラスに備わっている共通メソッドに対して何もしないということはゾンビに対して不必要
        // ゾンビは動物とは異なる概念であり、別のクラス階層で扱うべき
        // _/_/_/_/_/_/_/_/_/_/
        // TODO jflute 次回1on1にて (2025/10/20)
    }
}
