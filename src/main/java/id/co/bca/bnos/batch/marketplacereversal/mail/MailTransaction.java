package id.co.bca.bnos.batch.marketplacereversal.mail;

// import java.util.List;

import java.util.Map;

public class MailTransaction {

    public static final String MAIL_TYPE_BRANCH = "BRANCH";
    public static final String MAIL_TYPE_APPROVAL = "APPROVAL";

    private Long key;
    private String mailType;
    private String subject;
    private String replyTo;
    private CharSequence[] recipients;
    private String mailCC;
    private String sender;
    private Map<CharSequence, CharSequence> datas;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public CharSequence[] getRecipients() {
        return recipients;
    }

    public void setRecipients(CharSequence[] recipients) {
        this.recipients = recipients;
    }

    public String getMailCC() {
        return mailCC;
    }

    public void setMailCC(String mailCC) {
        this.mailCC = mailCC;
    }

    public Map<CharSequence, CharSequence> getDatas() {
        return datas;
    }

    public void setDatas(Map<CharSequence, CharSequence> datas) {
        this.datas = datas;
    }
}
