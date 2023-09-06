package id.co.bca.bnos.batch.marketplacereversal.mail.service;

import id.co.bca.bnos.batch.marketplacereversal.mail.MailRecord;
import id.co.bca.bnos.batch.marketplacereversal.mail.MailTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Arrays;

@Service
public class MailKafkaService {
    public static Logger LOG = LoggerFactory.getLogger(MailKafkaService.class);

    @Value("${bnos.kafka.mail.topic}")
    private String topic;

    private KafkaTemplate<Long, MailRecord> kafkaTemplate;

    public MailKafkaService(KafkaTemplate<Long, MailRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final MailTransaction data){
        MailRecord mailRecord= MailRecord.newBuilder()
        .setDatas(data.getDatas())
        // .setMailCC(data.getMailCC())
        .setMailCC(data.getSender())
        .setMailType(data.getMailType())
        .setRecipients(Arrays.asList(data.getRecipients()))
        // .setReplyTo(data.getReplyTo())
        .setReplyTo(data.getSender())
        .setSender(data.getSender())
        .setSubject(data.getSubject())
        .build();
//         ListenableFuture<SendResult<String, MailTransaction>> future  = kafkaTemplate.send(topic, data);
        ListenableFuture<SendResult<Long, MailRecord>> future  = kafkaTemplate.send(topic, data.getKey(), mailRecord);
        future.addCallback(new ListenableFutureCallback<SendResult<Long, MailRecord>>() {

            @Override
            public void onSuccess(SendResult<Long, MailRecord> result) {
                // TODO Auto-generated method stub
                LOG.debug("Send mail to MQ success");
            }

            @Override
            public void onFailure(Throwable ex) {
                // TODO Auto-generated method stub
                LOG.debug("Send mail to MQ error {}", ex.getMessage());
            }
        });
    }
}
