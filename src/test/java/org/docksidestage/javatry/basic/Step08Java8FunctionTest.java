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
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.docksidestage.javatry.basic.st8.St8DbFacade;
import org.docksidestage.javatry.basic.st8.St8Member;
import org.docksidestage.javatry.basic.st8.St8Withdrawal;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Java8 functions. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author TaiGa00-ishi
 */
public class Step08Java8FunctionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                              Lambda
    //                                                                              ======
    // -----------------------------------------------------
    //                                              Callback
    //                                              --------
    /**
     * Are all the strings by log() methods in callback processes same? (yes or no) <br>
     * (コールバック処理の中で出力しているログの文字列はすべて同じでしょうか？ (yes or no))
     */
    public void test_java8_lambda_callback_basic() {
        String title = "over";

        log("...Executing named class callback(!?)");
        helpCallbackConsumer(new St8BasicConsumer(title));

        log("...Executing anonymous class callback");
        helpCallbackConsumer(new Consumer<String>() {
            @Override
            public void accept(String stage) {
                log(stage + ": " + title);
            }
        });

        log("...Executing lambda block style callback");
        helpCallbackConsumer(stage -> {
            log(stage + ": " + title);
        });

        log("...Executing lambda expression style callback");
        helpCallbackConsumer(stage -> log(stage + ": " + title));

        // your answer? => yes

        // cannot reassign because it is used at callback process
        //title = "wave";
    }
    // 4種類どれもコールバックとしてbroadway, dockside : over, hangerを出力する
    // 1つ目はコールバック関数を呼び出す際にSt8BasicConsumerクラスのインスタンスを渡す(titleにoverが渡っている)
    // 実際にコールバックが呼ばれた際にSt8BasicConsumerのインスタンスの.acceptも呼ばれて2つ目のログの時にちゃんとdockside : overになる
    // 2つ目の処理は先ほどのコールバック関数に渡すものを名前を持たないConsumer<String>型を返す無名な関数を定義している
    // なのでacceptも呼ばれて2つ目のログの時にちゃんとdockside : overになる
    // 3つ目は無名の関数の別の書き方(ラムダ関数の書き方)をしただけなので、同じ処理である
    // 4つ目は3つ目の関数の時の処理が1行だけならさらに短く省略できるので、同じ処理をしている短縮版である。なので同じ結果が出る。
    
    // #1on1: コールバックとは？ (2026/01/05)
    // A -> B
    // A <- B // 厳密にはAというよりかはA'だけど
    // コールバックは追うのなかなか大変だった by いしはらさん
    // わかります、これはこれで読むのコツが要る by jflute

    /**
     * What is order of strings by log(). (write answer as comma-separated) <br>
     * (ログに出力される文字列の順番は？ (カンマ区切りで書き出しましょう))
     */
    public void test_java8_lambda_callback_order() {
        log("harbor");
        helpCallbackConsumer(stage -> {
            log(stage);
        });
        log("lost river");
        // your answer? => harbor, broadway, dockside, hanger, lost river
        // コールバック関数で呼び出しているログの２つ目もシンプルなlogを出すだけのラムダ関数なので、上から順番に出力される
    }

    private class St8BasicConsumer implements Consumer<String> {

        private final String title;

        public St8BasicConsumer(String title) {
            this.title = title;
        }

        @Override
        public void accept(String stage) {
            log(stage + ": " + title);
        }
    }

    private void helpCallbackConsumer(Consumer<String> oneArgLambda) {
        log("broadway");
        oneArgLambda.accept("dockside");
        log("hangar");
    }

    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_lambda_callback_valueRoad() {
        String label = "number";
        String sea = helpCallbackFunction(number -> {
            return label + ": " + number;
        });
        log(sea); // your answer? => number: 7
        // numberの場所にはcallback関数内で7がセットされるのでseaの中身として"number: 7"となる
    }

    private String helpCallbackFunction(Function<Integer, String> oneArgLambda) {
        return oneArgLambda.apply(7);
    }

    // -----------------------------------------------------
    //                                         Convert Style
    //                                         -------------
    /**
     * Change callback style like this:
     * <pre>
     * o sea: to lambda block style
     * o land: to lambda expression style
     * o piari: to lambda block style
     * </pre>
     * (このようにコールバックスタイルを変えてみましょう:)
     * <pre>
     * o sea: BlockのLambda式に
     * o land: ExpressionのLambda式に
     * o piari: BlockのLambda式に
     * </pre>
     */
    public void test_java8_lambda_convertStyle_basic() {
        helpCallbackSupplier(() -> {
            return "broadway".concat("aaaaaaaaaaaa").replace('a', 'b').substring(111).toLowerCase();
        }); // sea

        helpCallbackSupplier(() -> "dockside"); // land

        helpCallbackSupplier(() -> {
            return "hangar";
        }); // piari
    }

    // memo Block式は{} と returnがある
    // expression型は{}がなく -> の先にreturnがない
    // #1on1: DBFluteの例とかで、コードデザイン的にblockかexpressionを使い分ける話 (2026/01/19)

    private void helpCallbackSupplier(Supplier<String> oneArgLambda) {
        String supplied = oneArgLambda.get();
        log(supplied);
    }

    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_concept() {
        St8Member oldmember = new St8DbFacade().oldselectMember(1);
        if (oldmember != null) {
            log(oldmember.getMemberId(), oldmember.getMemberName());
        }
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        // your answer? => yes
        // selectMemberのラムダ式でnullチェックをした後でoldselectMemberをしているのと同じなので、自ずと同じ文字列が出ると読める。

        // #1on1: Optionalの根源的なメリット(やりたいこと)は、もうこれ。(2026/01/19)
        // oldスタイルよりも安全、事前にないかもしれないことを開発者に強制的に意識させる。
        // Java8 (2015年くらい) にようやく入った...なんでそんな遅かったんだろう推測。 (2026/01/19)
        // ifPresent()の存在。ある程度のシンタックスシュガーがないといくら安全でも流行らない(かも!?)。
        // さらにmap()でのチェーンによる制御。
        // TypeScript, Kotlinでの話。
        // に比べればJavaのOptionalは若干中途半端話。
    }

    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_ifPresent() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        optMember.ifPresent(member -> {
            log(member.getMemberId(), member.getMemberName());
        });
        // your answer? => yes
        // 二つ目のここの処理は一つ目の処理のブロックタイプのラムダ式をしているだけなので、同じ出力が出ると言える。
        // optMember.ifPresent(member -> {
        //            log(member.getMemberId(), member.getMemberName());
        //        });
    }

    /**
     * What string is sea, land, piari, bonvo, dstore, amba variables at the method end? <br>
     * (メソッド終了時の変数 sea, land, piari, bonvo, dstore, amba の中身は？)
     */
    public void test_java8_optional_map_flatMap() {
        St8DbFacade facade = new St8DbFacade();

        // traditional style
        St8Member oldmemberFirst = facade.oldselectMember(1); // 1, "broadway", new St8Withdrawal(11, "music")
        String sea;
        if (oldmemberFirst != null) {
            St8Withdrawal withdrawal = oldmemberFirst.oldgetWithdrawal(); // St8Withdrawal(11, "music")
            if (withdrawal != null) {
                sea = withdrawal.oldgetPrimaryReason(); // music　not nullなので基本的にelseをskip
                if (sea == null) {
                    sea = "*no reason1: the PrimaryReason was null";
                }
            } else {
                sea = "*no reason2: the Withdrawal was null";
            }
        } else {
            sea = "*no reason3: the selected Member was null";
        }

        Optional<St8Member> optMemberFirst = facade.selectMember(1);
        // St8Member(1, "broadway", new St8Withdrawal(11, "music"))

        // map style
        String land = optMemberFirst.map(mb -> mb.oldgetWithdrawal())// St8Withdrawal(11, "music")
                .map(wdl -> wdl.oldgetPrimaryReason())// music
                .orElse("*no reason: someone was not present");

        // flatMap style
        String piari = optMemberFirst.flatMap(mb -> mb.getWithdrawal())// St8Withdrawal(11, "music")
                .flatMap(wdl -> wdl.getPrimaryReason())// music
                .orElse("*no reason: someone was not present");

        // flatMap and map style
        String bonvo = optMemberFirst.flatMap(mb -> mb.getWithdrawal())// St8Withdrawal(11, "music")
                .map(wdl -> wdl.oldgetPrimaryReason())// music
                .orElse("*no reason: someone was not present");

        String dstore = facade.selectMember(2)// 2, "dockside", new St8Withdrawal(12, null)
                .flatMap(mb -> mb.getWithdrawal())// St8Withdrawal(12, null)
                .map(wdl -> wdl.oldgetPrimaryReason()) // nullなのでorElseに吸い込まれる
                .orElse("*no reason: someone was not present");

        String amba = facade.selectMember(3)// 3, "hangar", null
                .flatMap(mb -> mb.getWithdrawal())// empty()なのでnullが返ってくる
                .flatMap(wdl -> wdl.getPrimaryReason())// null
                .orElse("*no reason: someone was not present");

        int defaultWithdrawalId = -1;
        Integer miraco = facade.selectMember(2)// 2, "dockside", new St8Withdrawal(12, null)
                .flatMap(mb -> mb.getWithdrawal()) // St8Withdrawal(12, null)
                .map(wdl -> wdl.getWithdrawalId()) // ID here 12
                .orElse(defaultWithdrawalId);

        log(sea); // your answer? => music
        log(land); // your answer? => music
        log(piari); // your answer? => music
        log(bonvo); // your answer? => music
        log(dstore); // your answer? => *no reason: someone was not present
        log(amba); // your answer? => *no reason: someone was not present
        log(miraco); // your answer? => 12
        
        // TODO jflute 次回1on1, map/flatMap() (2026/01/19)
    }

    /**
     * What string is sea variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_optional_orElseThrow() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(2); // 2, "dockside", new St8Withdrawal(12, null)
        St8Member member = optMember.orElseThrow(() -> new IllegalStateException("over")); // 2, "dockside", new St8Withdrawal(12, null)
        String sea = "the";
        try {
            String reason = member.getWithdrawal().map(wdl -> wdl.oldgetPrimaryReason()).orElseThrow(() -> {
                return new IllegalStateException("wave");
            });// IllegalStateException("wave") -> catchへ
            sea = reason;
        } catch (IllegalStateException e) {
            sea = e.getMessage();// wave
        }
        log(sea); // your answer? => wave
    }

    // ===================================================================================
    //                                                                          Stream API
    //                                                                          ==========
    /**
     * What string is sea, land variables at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_java8_stream_concept() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        // 1, "broadway", new St8Withdrawal(11, "music")
        //  St8Purchase(111, 100));
        //  St8Purchase(112, 200));
        //  St8Purchase(113, 200));
        //  St8Purchase(114, 300));
        // 2, "dockside", new St8Withdrawal(12, null)
        // 3, "hangar", null
        //  St8Purchase(131, 700));
        //  St8Purchase(132, 800)
        List<String> oldfilteredNameList = new ArrayList<>();
        for (St8Member member : memberList) {
            if (member.getWithdrawal().isPresent()) {// withdrawalがnullでないものがaddされる
                oldfilteredNameList.add(member.getMemberName());// broadway, dockside
            }
        }
        String sea = oldfilteredNameList.toString();
        log(sea); // your answer? => broadwaydockside

        List<String> filteredNameList = memberList.stream() //
                .filter(mb -> mb.getWithdrawal().isPresent()) //
                .map(mb -> mb.getMemberName()) //
                .collect(Collectors.toList());
        String land = filteredNameList.toString();
        log(land); // your answer? => broadwaydockside
    }
    // 実際は[broadway, dockside]
    // 配列型をそのままstringに置き換えているので

    /**
     * What string is sea, variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_stream_map_flatMap() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        // 1, "broadway", new St8Withdrawal(11, "music")
        //  St8Purchase(111, 100));
        //  St8Purchase(112, 200));
        //  St8Purchase(113, 200));
        //  St8Purchase(114, 300));
        // 2, "dockside", new St8Withdrawal(12, null)
        // 3, "hangar", null
        //  St8Purchase(131, 700));
        //  St8Purchase(132, 800)
        int sea = memberList.stream()
                .filter(mb -> mb.getWithdrawal().isPresent())
                // 1, "broadway", new St8Withdrawal(11, "music")
                //  St8Purchase(111, 100));
                //  St8Purchase(112, 200));
                //  St8Purchase(113, 200));
                //  St8Purchase(114, 300));
                // 2, "dockside", new St8Withdrawal(12, null)
                .flatMap(mb -> mb.getPurchaseList().stream())
                .filter(pur -> pur.getPurchaseId() > 100)
                //  St8Purchase(111, 100));
                //  St8Purchase(112, 200));
                //  St8Purchase(113, 200));
                //  St8Purchase(114, 300));
                .mapToInt(pur -> pur.getPurchasePrice())
                .distinct() // distinct名前的にユニークなものを持ってくるとして、sumとチェーンすると100 + 200 + 300
                .sum();
        log(sea); // your answer? => 600
    }

    // *Stream API will return at Step12 again, it's worth the wait!
}
