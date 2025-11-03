package org.docksidestage.javatry.basic.st6.os;

/**
 * OldWindowsを表すクラスです。
 * <p>
 * このクラスは、OSクラスを継承し、独自のファイルセパレーターとディレクトリ構造を持っています。
 * </p>
 * @author taiGa00-ishi
 */
public class OldWindows extends St6OperationSystem {
    public OldWindows(String loginId) {
        super(loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "\\"; // Old Windows固有の実装
    }

    @Override
    protected String getUserDirectory() {
        return "/Documents and Settings/" + loginId; // Old Windows固有の実装
    }
}
