package org.docksidestage.javatry.basic.st6.os;

/**
 * Windowsを表すクラスです。
 * <p>
 * このクラスは、OSクラスを継承し、独自のファイルセパレーターとディレクトリ構造を持っています。
 * </p>
 * @author taiGa00-ishi
 */
public class Windows extends St6OperationSystem {
    public Windows(String loginId) {super(loginId);}

    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        // done ishihara super.は不要です。(というか本来は this. を使うところでそれも省略) by jflute (2025/10/06)
        // コンストラクタでloginIdで受け取っていましたね
        // done ishihara Cドライブかどうか？の表現がOldWindowsの方と不統一なので合わせましょう by jflute (2025/10/06)
        // (まあ、ここではCドライブは気にしない実装にしちゃってOK)
        return "/Users/" + loginId;
    }
}
