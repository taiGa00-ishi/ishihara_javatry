package org.docksidestage.javatry.basic.st6.dbms;

/**
 * データベースを表す抽象クラスです。
 * <p>
 * このクラスは、異なるデータベースシステムに対応するための共通のメソッドを提供する。
 * </p>
 * @author taiGa00-ishi
 */
public abstract class Database {

    public abstract String buildPagingQuery(int pageSize, int pageNumber);

}
