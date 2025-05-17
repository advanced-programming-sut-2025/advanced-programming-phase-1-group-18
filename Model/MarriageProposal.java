package Model;


import Model.Items.Item;

public class MarriageProposal {
    private final Player proposer;
    private final Player receiver;
    private final Item ring;

    public MarriageProposal(Player proposer, Player receiver, Item ring) {
        this.proposer = proposer;
        this.receiver = receiver;
        this.ring = ring;
    }

    public Player getProposer() {
        return proposer;
    }

    public Player getReceiver() {
        return receiver;
    }

    public Item getRing() {
        return ring;
    }
}
