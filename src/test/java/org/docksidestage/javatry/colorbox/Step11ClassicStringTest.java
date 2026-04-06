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
package org.docksidestage.javatry.colorbox;

import java.util.List;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author TaiGa00-ishi
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() { // example, so begin from the next method
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMax_colorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        // TODO ishihara not found のときに log("*not found") 的なものが出るようにお願いします by jflute (2026/04/06)
        if (!colorBoxList.isEmpty()) {
            int max = 0;
            String maxColorName = "";

            // #1on1: ここはあえて基本 int iループを使ってみたということ // (2026/04/06)
            for (int i = 0; i < colorBoxList.size(); i++) {
                ColorBox colorBox = colorBoxList.get(i);
                BoxColor boxColor = colorBox.getColor();
                String colorName = boxColor.getColorName();
                if (colorName.length() > max) {
                    max = colorName.length();
                    maxColorName = colorName;
                }
            }
            log(max + " (" + maxColorName + ")");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax_stringContent() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            // TODO ishihara longestWordとしてるくらいなら、longestLengthが嬉しい by jflute (2026/04/06)
            // この二つの変数は関連性が強く、連動するもの、であることをぱっと見でわかりたい。
            // 一方で、スコープ短いからすっきり変数にする方向性であれば、逆にwordでいいのかなと。
            // (一方で一方で、jfluteの好みだと、maxとcurrentのlengthを区別したい)
            int length = 0;
            String longestWord = "";
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace space : colorBox.getSpaceList()) {
                    Object content = space.getContent();
                    if (content instanceof String) {
                        String str = (String) content;
                        if (str.length() > length) {
                            length = str.length();
                            longestWord = str;
                        }
                    }
                }
            }
            log(length + " (" + longestWord + ")");
        }
    }

    // どうしてもfor分だとインデントたくさんあるような書き方になってしまう。
    // 他にスッキリかける方法はあるのだろうか？
    //
    // #1on1: ifのところは、判定を逆転させて、continueすることでインデントを減らせるが... (2026/04/06)
    // あまり論理が直感的ではなくなるかもなので本末転倒感もある。
    // そういう意味ではインデント自体は悪く無く、人間に直感性を与えるための道具ではある。
    // ただ、あまりに多いと全体像が把握しづらくなるのかも。
    //
    // ループの方は、Stream APIをつかわない前提であれば、なかなか二重ループを無くすのは難しく...
    // 事前にallSpaceListとか作っておけば減らせなくはないけど、わざわざそれをやるか？ってのもある。
    // あと、privateメソッド切り出しで色々チャレンジしてみたけど、どれも微妙。
    // ２つのローカル変数で更新しないといけないので、複数の戻り値を戻す入れ物クラスとか必要になっちゃう。
    //
    // ただ、longestWord だけで制御できなくはないので、更新変数が一個だけなら↑のやり方も採用できるかも。
    // if (str.length() > longestWord.length()) {
    // length() は、value.length してるだけなので何度も読んでもまあパフォーマンスも全然気にならないし。
    //
    // 中でのif文。
    /*
if (length > 0) {
    log(length + " (" + longestWord + ")");
} else {
    log("*not found");
}
     */
    // maxLength=0 で初期化していると、contentの中、空文字だけが見つかった場合、
    // それが区別できなくなってしまう。(区別が必要かどうかはそのときの業務次第)
    // jfluteの個人的なポリシーだと、わりとnull初期化を使う。まだ探してないからないものはない。
    // -1とかだと、MINを求める時使えないとか、ある程度そのデフォルト値が妥当かどうか気にしないといけない。
    //
    // 少し余談、nullと空文字の混在のお話。
    // xxxxxxx?productName=&productStatus=...
    // DBのカラムに混在するとちょっとやっかい。

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (latter if same length) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (同じ長さのものがあれば後の方を))
     */
    public void test_length_findSecondMax_contentToString() {
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
    }

    // ===================================================================================
    //                                                                      Pickup Methods
    //                                                                      ==============
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
    }

    /**
     * What number character is starting with the late "ど" of string containing two or more "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
    }

    // ===================================================================================
    //                                                                 Welcome to Guardian
    //                                                                 ===================
    /**
     * What is total length of text of GuardianBox class in color-boxes? <br>
     * (カラーボックスの中に入っているGuardianBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToGuardian() {
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // these are very difficult exercises so you can skip
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
    }
}
