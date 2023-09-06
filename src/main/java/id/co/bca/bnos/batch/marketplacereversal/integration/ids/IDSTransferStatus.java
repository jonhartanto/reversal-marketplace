package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

public class IDSTransferStatus {
    Long id;
    Boolean transferProcess;
    Boolean transferStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTransferProcess() {
        return transferProcess;
    }

    public void setTransferProcess(Boolean transferProcess) {
        this.transferProcess = transferProcess;
    }

    public Boolean getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(Boolean transferStatus) {
        this.transferStatus = transferStatus;
    }
}
