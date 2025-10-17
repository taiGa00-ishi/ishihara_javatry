package org.docksidestage.javatry.basic.st6.os;

/**
 * Macを表すクラスです。
 * <p>
 * このクラスは、OSクラスを継承し、独自のファイルセパレーターとディレクトリ構造を持っています。
 * </p>
 * @author taiGa00-ishi
 */
public class Mac extends  St6OperationSystem {
    public Mac(String loginId) {
        super("Mac", loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "/";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
