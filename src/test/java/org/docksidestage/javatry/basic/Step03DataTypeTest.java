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
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author taiGa00-ishi
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1);
        land = piari.getYear();
        bonvo = bonvo.plusMonths(1);
        land = bonvo.getMonthValue();
        land--;
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land));
            sea = String.valueOf(addedDecimal);
        }
        log(sea); // your answer? => 18.4
    }

    // 色々変数がある中で最終的にはseaだけ見ているので逆算してseaの値について最後処理されているところを見た
    // seaにはaddedDecimalの値が入ることを発見
    // addedDecimalはambaにlandを足した値ということがわかる
    // ambaは9.4とわかる
    // landはまた色々と変数が操作されているのでそれを追っていくと9だとわかる
    // なので9.4と9を足した値は18.4がString型に変換されてseaに入ることがわかる
    // よって出力は18.4となると考えた。
    
    // #1on1: オブジェクト型のBooleanは、ほとんど使われる場面はない。二値を保証したいから。

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a';
        if (dohotel && dstore >= piari) {
            bonvo = sea;
            land = (short) bonvo;
            bonvo = piari;
            sea = (byte) land;
            if (amba == 2.3D) {
                sea = (byte) amba;
            }
        }
        if ((int) dstore > piari) {
            sea = 0;
        }
        log(sea); // your answer? => 0
    }

    // 実際は2
    // seaが最後に書きかわりそうなところから見ていった。
    // dstoreが1.1fとあり、piariが1とあるので条件が成立すると勘違いして0と考えた。
    // しかし、dstoreはfloat型でintにキャストされているので1.1fは1となり条件が成立しない。
    // そのため、seaは最後のif文には入らない
    // その前のif文では、dohotelがtrueであるため、bonvoはseaの値127が入る。
    // その後、landはbonvoの値127が入る。
    // その後、bonvoはpiariの値1が入る。
    // その後、seaはlandの値127が入る。
    // その後、ambaは2.3Dとあるので、最後のif文に入る。
    // そのため、seaは(2.3Dをbyteにキャストした値)2が入る。
    // done ishihara [いいね] ややこしいですよね(^^。縮小型変換などは要注意というところです。 by jflute (2025/07/28)
    // 通常、そもそもこういうプログラムは書かないようにするって感じですね(^^
    
    // #1on1: プリミティブ型の現場での活用のされ方についての補足 (2025/07/28)

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName();
        log(sea); // your answer? => hangar
    }

    // seaに代入するstage.getStageName()はSt3ImmutableStageのインスタンスであるsatgeのメソッドgetStageName()を呼び出している。
    // インスタンス生成の際に"hangar"という文字列を引数に渡しているので、getStageName()はその文字列を返す。
    // #1on1: 自分でimmutableなクラスを作るってなったら、こんなかんじのクラスになる (2025/07/28)

    private static class St3ImmutableStage {

        private final String stageName;

        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }
        
        public String getStageName() {
            return stageName;
        }
    }
}
