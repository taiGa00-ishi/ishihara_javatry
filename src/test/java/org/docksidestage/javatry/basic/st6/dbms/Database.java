package org.docksidestage.javatry.basic.st6.dbms;

// #1on1: dbmsというキーワードもあり、Database Management System
// 一つの用語が、狭義、広義、で色々と使われることがある。e.g. データベース、プロジェクト
// なので(かどうかわからないけど)、DBの製品の違いを表すときは、DBMSという言葉がよく使われる。
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
