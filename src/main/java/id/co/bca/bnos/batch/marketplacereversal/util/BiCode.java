package id.co.bca.bnos.batch.marketplacereversal.util;

public enum BiCode {

    domestic_bank(0),
    foreign_bank(3);

    public final int biCode;

    private BiCode(int biCode) {
        this.biCode = biCode;
    }
}
