package org.docksidestage.bizfw.basic.buyticket;

/**
 * チケットの購入結果を表すクラスです。
 * <p>
 * このクラスは、購入されたチケットとお釣りの金額を保持します。
 * </p>
 */
public class TicketBuyResult {

    /** チケットのインスタンス。 */
    private final Ticket ticket;
    /** お釣りの金額。 */
    private final int change;

    /**
     * 新しい購入結果インスタンスを構築します。
     * @param ticket 購入されたチケット。
     * @param change お釣りの金額。
     */
    public TicketBuyResult(Ticket ticket, int change) {
        this.ticket = ticket;
        this.change = change;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }
}
