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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.GuardianBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.GuardianBoxTextNotFoundException;
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
        // done ishihara not found のときに log("*not found") 的なものが出るようにお願いします by jflute (2026/04/06)
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
        } else {
            log("*not found");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax_stringContent() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            // done ishihara longestWordとしてるくらいなら、longestLengthが嬉しい by jflute (2026/04/06)
            // この二つの変数は関連性が強く、連動するもの、であることをぱっと見でわかりたい。
            // 一方で、スコープ短いからすっきり変数にする方向性であれば、逆にwordでいいのかなと。
            // (一方で一方で、jfluteの好みだと、maxとcurrentのlengthを区別したい)
            int longestLength = 0;
            String longestWord = "";
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace space : colorBox.getSpaceList()) {
                    Object content = space.getContent();
                    if (content instanceof String) {
                        String str = (String) content;
                        if (str.length() > longestLength) {
                            longestLength = str.length();
                            longestWord = str;
                        }
                    }
                }
            }
            log(longestLength + " (" + longestWord + ")");
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
        // 2番目を探す
        // maxより小さいmaxと考える
        // maxを最初のループで見つけて、それより小さいmaxを探すパターン(2回maxを探す処理をする)
        // もしくはmaxの値より小なりの文字列の長さの時かつその次のmaxより小さい文字列の時にその文字を格納する(1つのループ処理内で一気にするパターン)
        // 考慮カラーボックスの値が文字列でない時はtoStringを呼び出す
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            // done ishihara もし、min側を探すとなった場合は、初期値はどうする？ by jflute (2026/04/26)
            // lengthでの最長の2147483647を設定する？
            int maxLength = 0;
            String maxString = "";
            int secondMaxLength = 0;
            String secondMaxString = "";
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace space : colorBox.getSpaceList()) {
                    Object content = space.getContent();
                    if (content != null) {
                        String str = content instanceof String ? (String) content : content.toString();
                        // 同じ長さなら後の方を優先するため >= を使う
                        if (str.length() >= maxLength) {
                            secondMaxLength = maxLength;
                            secondMaxString = maxString;
                            maxLength = str.length();
                            maxString = str;
                        } else if (str.length() >= secondMaxLength) {
                            secondMaxLength = str.length();
                            secondMaxString = str;
                        }
                    }
                }
            }
            log(secondMaxLength + " (" + secondMaxString + ")");
        }
    }
    // なんか出力が仰々しいけどいけているのだろうか？
    // toStringでした後の中身にも何か考慮しないといけない事項がある？
    // done ishihara [へんじ] 仰々しい中身が入ってるから大丈夫だと思う by jflute (2026/04/26)

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sum = 0;
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace space : colorBox.getSpaceList()) {
                Object content = space.getContent();
                if (content instanceof String) {
                    // contentはObjectなのでStringをキャストしてあげないといけないっぽい
                    // done ishihara [ふぉろー]yes, instanceofは判定しただけなので by jflute (2026/04/26)
                    //sum += ((String) content).length();
                    // #1on1: Editorが ((String) content).contains(s); を補完してくれたりする (2026/05/11)
                    // contentがStringであることをわかってて、dotのメソッド補完でもStringもメソッドが出てくる。
                }
            }
        }
        log(sum);
    }

    // ===================================================================================
    //                                                                      Pickup Methods
    //                                                                      ==============
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        // done ishihara [いいね]素晴らしいその配慮 by jflute (2026/04/26)
        // Waterで始まっているものが１つと限らないのでリストでresultを持っておきたい
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        // done ishihara リストなので、複数を示す単語にしたいところ。results でもいいし resultList でも by jflute (2026/04/26)
        List<String> results = new ArrayList<>();
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace space : colorBox.getSpaceList()) {
                Object content = space.getContent();
                if (content instanceof String && ((String) content).startsWith("Water")) {
                    results.add(colorBox.getColor().getColorName());
                    break;
                }
            }
        }
        log(results);
    }

    /**
     * What number character is starting with the late "ど" of string containing two or more "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        // 文字列の要素でどをincludeしているものだけに探索してカウントが2つ以上のときに最後の「ど」のindexも持っておく
        // 「ど」が見つかるたびに都度都度indexを更新していくイメージ
        // これも１つだけとは限らないので、ログとしては[見つかった文字列 : 最後の「ど」の位置]で出力
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> result = new ArrayList<>();
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace space : colorBox.getSpaceList()) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String str = (String) content;
                    if (str.contains("ど")) {
                        int count = 0;
                        int lastIndex = -1;
                        for (int i = 0; i < str.length(); i++) {
                            if (str.charAt(i) == 'ど') {
                                count++;
                                lastIndex = i; // 見つかるたびに更新
                            }
                        }
                        if (count >= 2) {
                            // 「何文字目」は1始まりなので +1
                            result.add(str + " : " + (lastIndex + 1));
                        }
                    }
                }
            }
        }
        log(result);
    }

    // indexOfとlastIndexOfのコラボでも作れたっぽい
    // done ishihara [ふぉろー] でもベタに書いてみるトレーニングになったということで(^^ by jflute (2026/04/26)
    // #1on1: if (str.indexOf("ど") != str.lastIndexOf("ど")) {

    // ===================================================================================
    //                                                                 Welcome to Guardian
    //                                                                 ===================
    /**
     * What is total length of text of GuardianBox class in color-boxes? <br>
     * (カラーボックスの中に入っているGuardianBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToGuardian() {
        // spaceがasGuardianの時にGuardianの順序に従ってtextを聞く、合計を出す
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sum = 0;
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace space : colorBox.getSpaceList()) {
                Object content = space.getContent();
                if (content instanceof GuardianBox) {
                    GuardianBox guardian = (GuardianBox) content;
                    guardian.wakeUp();
                    guardian.allowMe();
                    guardian.open();
                    try {
                        sum += guardian.getText().length();
                    } catch (GuardianBoxTextNotFoundException e) {
                        // textがnullのGuardianBoxはスキップ
                    }
                }
            }
        }
        log(sum);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        // とりあえず同じ感じでループを回してmapの要素の時に指定のフォーマットに落とし込む
        // 追記
        // 次の問題で再帰的にmapを処理するようにしたので、シンプルに関数を呼び出すだけにした
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace space : colorBox.getSpaceList()) {
                Object content = space.getContent();
                if (content instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) content;
                    log(buildMapString(map, false));
                }
            }
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        // 同じ感じでループさせて、またmapが中にあれば同じような処理をネストして行う
        // 何重もあったら書ききれないので再帰的な関数を作って対応する
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            for (BoxSpace space : colorBox.getSpaceList()) {
                Object content = space.getContent();
                if (content instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) content;
                    log(buildMapString(map, true));
                }
            }
        }
    }

    // #1on1: とっても綺麗にできてる。再帰呼び出ししっかり使いこなせている (2026/05/11)
    private String buildMapString(Map<String, Object> map, boolean nested) {
        List<String> entries = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            String valueString;
            if (nested && value instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> nestedMap = (Map<String, Object>) value;
                valueString = buildMapString(nestedMap, true);
            } else {
                valueString = String.valueOf(value);
            }
            entries.add(entry.getKey() + " = " + valueString);
        }
        return "map:{ " + String.join(" ; ", entries) + " }";
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
        // まず理解できるところまで
        // 1,whiteを抽出　2,upperを取得　3,contentを取得　4,textを取得
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if (colorBox.getColor().getColorName().equals("white")) {
                BoxSpace upperSpace = colorBox.getSpaceList().get(0);
                Object content = upperSpace.getContent();
                if (content instanceof YourPrivateRoom.SecretBox) {
                    YourPrivateRoom.SecretBox secretBox = (YourPrivateRoom.SecretBox) content;
                    // ここで取得したものをmapに変換しないといけない
                    Map<String, Object> map = mapStringToMap(secretBox.getText());
                    log(map.toString());
                }
            }
        }
    }

    // ex.map:{ dockside = over ; hangar = mystic ; broadway = bbb }
    // ex.map:{ dockside = over ; hangar = map:{ mystic = performance ; shadow = musical } ; broadway = bbb }
    private Map<String, Object> mapStringToMap(String content) {
        Map<String, Object> result = new LinkedHashMap<>();
        String innerMapString = content.substring("map:{ ".length(), content.lastIndexOf(" }"));
        // こうなっているはず
        // dockside = over ; hangar = map:{ mystic = performance ; shadow = musical } ; broadway = bbb
        // 単純に;で分割すると事故る
        // こんな感じで分割できたらいい感じかも
        // dockside = over
        // hangar = map:{ mystic = performance ; shadow = musical }　<- ここでvalueに対して再帰させる　
        // broadway = bbb
        // こんな感じで分割できたら＝で区切ってputしていく
        for (String entry : splitMapStrings(innerMapString)) {
            // entry dockside = over
            // {dockside=over}
            // entry hangar = map:{ mystic = performance ; shadow = musical }
            // 別マップに登録されるとまずい
            // putしながら再帰投げる
            // 再帰先　map:{ mystic = performance ; shadow = musical }
            // mystic = performance ; shadow = musical
            // splitMapStrings(mystic = performance ; shadow = musical)
            // [0]mystic = performance [1]shadow = musical
            // どうなる？ ikesou
            int index = entry.indexOf(" = ");
            String key = entry.substring(0, index);
            String value = entry.substring(index + 2);
            if (entry.contains("map:{")) {
                result.put(key, mapStringToMap(value));
            } else {
                result.put(key, value);
            }
        }
        return result;
    }

    // ex dockside = over ; hangar = map:{ mystic = performance ; shadow = musical } ; broadway = bbb
    private List<String> splitMapStrings(String input) {
        List<String> output = new ArrayList<>();
        int depth = 0;
        int start = 0;
        // dockside = over ; hangar = map:{ mystic = performance ; shadow = musical } ; broadway = bbb
        // [0] dockside = over
        // depth 1
        // }のときdepth0になり、次の;でトリガー
        // [1] hangar = map:{ mystic = performance ; shadow = musical }
        // [2] broadway = bbb
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '{') {
                depth++;
            } else if (input.charAt(i) == '}') {
                depth--;
            } else if (input.charAt(i) == ';' && depth == 0) {
                output.add(input.substring(start, i - 1));
                start = i + 2;
            }
        }
        output.add(input.substring(start));
        return output;
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if (colorBox.getColor().getColorName().equals("white")) {
                BoxSpace middleSpace = colorBox.getSpaceList().get(1);
                BoxSpace lowerSpace = colorBox.getSpaceList().get(2);
                for (BoxSpace space : Arrays.asList(middleSpace, lowerSpace)) {
                    Object content = space.getContent();
                    if (content instanceof YourPrivateRoom.SecretBox) {
                        YourPrivateRoom.SecretBox secretBox = (YourPrivateRoom.SecretBox) content;
                        // 順番が変になるな
                        // Claude君曰く、LinkedHashMap使ったら入力順でマップを並べてくれるみたい
                        // なので関数内で宣言するマップはLinkedでした
                        // pointerみたいな感じで連結しているのか？知らんけど
                        Map<String, Object> map = mapStringToMap(secretBox.getText());
                        log(map.toString());
                    }
                }
            }
        }
    }
}
