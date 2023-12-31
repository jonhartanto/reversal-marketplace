/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package id.co.bca.bnos.batch.marketplacereversal.mail;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class MailRecord extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -8746737563080690312L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MailRecord\",\"namespace\":\"id.co.bca.bnos.integration.mail\",\"fields\":[{\"name\":\"mailType\",\"type\":\"string\"},{\"name\":\"replyTo\",\"type\":\"string\"},{\"name\":\"recipients\",\"type\":{\"type\":\"array\",\"items\":\"string\"}},{\"name\":\"mailCC\",\"type\":\"string\"},{\"name\":\"sender\",\"type\":\"string\"},{\"name\":\"subject\",\"type\":\"string\"},{\"name\":\"datas\",\"type\":{\"type\":\"map\",\"values\":\"string\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<MailRecord> ENCODER =
      new BinaryMessageEncoder<MailRecord>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<MailRecord> DECODER =
      new BinaryMessageDecoder<MailRecord>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<MailRecord> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<MailRecord> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<MailRecord>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this MailRecord to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a MailRecord from a ByteBuffer. */
  public static MailRecord fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public CharSequence mailType;
  @Deprecated public CharSequence replyTo;
  @Deprecated public java.util.List<CharSequence> recipients;
  @Deprecated public CharSequence mailCC;
  @Deprecated public CharSequence sender;
  @Deprecated public CharSequence subject;
  @Deprecated public java.util.Map<CharSequence, CharSequence> datas;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public MailRecord() {}

  /**
   * All-args constructor.
   * @param mailType The new value for mailType
   * @param replyTo The new value for replyTo
   * @param recipients The new value for recipients
   * @param mailCC The new value for mailCC
   * @param sender The new value for sender
   * @param subject The new value for subject
   * @param datas The new value for datas
   */
  public MailRecord(CharSequence mailType, CharSequence replyTo, java.util.List<CharSequence> recipients, CharSequence mailCC, CharSequence sender, CharSequence subject, java.util.Map<CharSequence, CharSequence> datas) {
    this.mailType = mailType;
    this.replyTo = replyTo;
    this.recipients = recipients;
    this.mailCC = mailCC;
    this.sender = sender;
    this.subject = subject;
    this.datas = datas;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public Object get(int field$) {
    switch (field$) {
    case 0: return mailType;
    case 1: return replyTo;
    case 2: return recipients;
    case 3: return mailCC;
    case 4: return sender;
    case 5: return subject;
    case 6: return datas;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, Object value$) {
    switch (field$) {
    case 0: mailType = (CharSequence)value$; break;
    case 1: replyTo = (CharSequence)value$; break;
    case 2: recipients = (java.util.List<CharSequence>)value$; break;
    case 3: mailCC = (CharSequence)value$; break;
    case 4: sender = (CharSequence)value$; break;
    case 5: subject = (CharSequence)value$; break;
    case 6: datas = (java.util.Map<CharSequence, CharSequence>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'mailType' field.
   * @return The value of the 'mailType' field.
   */
  public CharSequence getMailType() {
    return mailType;
  }

  /**
   * Sets the value of the 'mailType' field.
   * @param value the value to set.
   */
  public void setMailType(CharSequence value) {
    this.mailType = value;
  }

  /**
   * Gets the value of the 'replyTo' field.
   * @return The value of the 'replyTo' field.
   */
  public CharSequence getReplyTo() {
    return replyTo;
  }

  /**
   * Sets the value of the 'replyTo' field.
   * @param value the value to set.
   */
  public void setReplyTo(CharSequence value) {
    this.replyTo = value;
  }

  /**
   * Gets the value of the 'recipients' field.
   * @return The value of the 'recipients' field.
   */
  public java.util.List<CharSequence> getRecipients() {
    return recipients;
  }

  /**
   * Sets the value of the 'recipients' field.
   * @param value the value to set.
   */
  public void setRecipients(java.util.List<CharSequence> value) {
    this.recipients = value;
  }

  /**
   * Gets the value of the 'mailCC' field.
   * @return The value of the 'mailCC' field.
   */
  public CharSequence getMailCC() {
    return mailCC;
  }

  /**
   * Sets the value of the 'mailCC' field.
   * @param value the value to set.
   */
  public void setMailCC(CharSequence value) {
    this.mailCC = value;
  }

  /**
   * Gets the value of the 'sender' field.
   * @return The value of the 'sender' field.
   */
  public CharSequence getSender() {
    return sender;
  }

  /**
   * Sets the value of the 'sender' field.
   * @param value the value to set.
   */
  public void setSender(CharSequence value) {
    this.sender = value;
  }

  /**
   * Gets the value of the 'subject' field.
   * @return The value of the 'subject' field.
   */
  public CharSequence getSubject() {
    return subject;
  }

  /**
   * Sets the value of the 'subject' field.
   * @param value the value to set.
   */
  public void setSubject(CharSequence value) {
    this.subject = value;
  }

  /**
   * Gets the value of the 'datas' field.
   * @return The value of the 'datas' field.
   */
  public java.util.Map<CharSequence, CharSequence> getDatas() {
    return datas;
  }

  /**
   * Sets the value of the 'datas' field.
   * @param value the value to set.
   */
  public void setDatas(java.util.Map<CharSequence, CharSequence> value) {
    this.datas = value;
  }

  /**
   * Creates a new MailRecord RecordBuilder.
   * @return A new MailRecord RecordBuilder
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Creates a new MailRecord RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new MailRecord RecordBuilder
   */
  public static Builder newBuilder(Builder other) {
    return new Builder(other);
  }

  /**
   * Creates a new MailRecord RecordBuilder by copying an existing MailRecord instance.
   * @param other The existing instance to copy.
   * @return A new MailRecord RecordBuilder
   */
  public static Builder newBuilder(MailRecord other) {
    return new Builder(other);
  }

  /**
   * RecordBuilder for MailRecord instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MailRecord>
    implements org.apache.avro.data.RecordBuilder<MailRecord> {

    private CharSequence mailType;
    private CharSequence replyTo;
    private java.util.List<CharSequence> recipients;
    private CharSequence mailCC;
    private CharSequence sender;
    private CharSequence subject;
    private java.util.Map<CharSequence, CharSequence> datas;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.mailType)) {
        this.mailType = data().deepCopy(fields()[0].schema(), other.mailType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.replyTo)) {
        this.replyTo = data().deepCopy(fields()[1].schema(), other.replyTo);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.recipients)) {
        this.recipients = data().deepCopy(fields()[2].schema(), other.recipients);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.mailCC)) {
        this.mailCC = data().deepCopy(fields()[3].schema(), other.mailCC);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.sender)) {
        this.sender = data().deepCopy(fields()[4].schema(), other.sender);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.subject)) {
        this.subject = data().deepCopy(fields()[5].schema(), other.subject);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.datas)) {
        this.datas = data().deepCopy(fields()[6].schema(), other.datas);
        fieldSetFlags()[6] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing MailRecord instance
     * @param other The existing instance to copy.
     */
    private Builder(MailRecord other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.mailType)) {
        this.mailType = data().deepCopy(fields()[0].schema(), other.mailType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.replyTo)) {
        this.replyTo = data().deepCopy(fields()[1].schema(), other.replyTo);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.recipients)) {
        this.recipients = data().deepCopy(fields()[2].schema(), other.recipients);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.mailCC)) {
        this.mailCC = data().deepCopy(fields()[3].schema(), other.mailCC);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.sender)) {
        this.sender = data().deepCopy(fields()[4].schema(), other.sender);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.subject)) {
        this.subject = data().deepCopy(fields()[5].schema(), other.subject);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.datas)) {
        this.datas = data().deepCopy(fields()[6].schema(), other.datas);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'mailType' field.
      * @return The value.
      */
    public CharSequence getMailType() {
      return mailType;
    }

    /**
      * Sets the value of the 'mailType' field.
      * @param value The value of 'mailType'.
      * @return This builder.
      */
    public Builder setMailType(CharSequence value) {
      validate(fields()[0], value);
      this.mailType = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'mailType' field has been set.
      * @return True if the 'mailType' field has been set, false otherwise.
      */
    public boolean hasMailType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'mailType' field.
      * @return This builder.
      */
    public Builder clearMailType() {
      mailType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'replyTo' field.
      * @return The value.
      */
    public CharSequence getReplyTo() {
      return replyTo;
    }

    /**
      * Sets the value of the 'replyTo' field.
      * @param value The value of 'replyTo'.
      * @return This builder.
      */
    public Builder setReplyTo(CharSequence value) {
      validate(fields()[1], value);
      this.replyTo = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'replyTo' field has been set.
      * @return True if the 'replyTo' field has been set, false otherwise.
      */
    public boolean hasReplyTo() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'replyTo' field.
      * @return This builder.
      */
    public Builder clearReplyTo() {
      replyTo = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'recipients' field.
      * @return The value.
      */
    public java.util.List<CharSequence> getRecipients() {
      return recipients;
    }

    /**
      * Sets the value of the 'recipients' field.
      * @param value The value of 'recipients'.
      * @return This builder.
      */
    public Builder setRecipients(java.util.List<CharSequence> value) {
      validate(fields()[2], value);
      this.recipients = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'recipients' field has been set.
      * @return True if the 'recipients' field has been set, false otherwise.
      */
    public boolean hasRecipients() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'recipients' field.
      * @return This builder.
      */
    public Builder clearRecipients() {
      recipients = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'mailCC' field.
      * @return The value.
      */
    public CharSequence getMailCC() {
      return mailCC;
    }

    /**
      * Sets the value of the 'mailCC' field.
      * @param value The value of 'mailCC'.
      * @return This builder.
      */
    public Builder setMailCC(CharSequence value) {
      validate(fields()[3], value);
      this.mailCC = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'mailCC' field has been set.
      * @return True if the 'mailCC' field has been set, false otherwise.
      */
    public boolean hasMailCC() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'mailCC' field.
      * @return This builder.
      */
    public Builder clearMailCC() {
      mailCC = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'sender' field.
      * @return The value.
      */
    public CharSequence getSender() {
      return sender;
    }

    /**
      * Sets the value of the 'sender' field.
      * @param value The value of 'sender'.
      * @return This builder.
      */
    public Builder setSender(CharSequence value) {
      validate(fields()[4], value);
      this.sender = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'sender' field has been set.
      * @return True if the 'sender' field has been set, false otherwise.
      */
    public boolean hasSender() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'sender' field.
      * @return This builder.
      */
    public Builder clearSender() {
      sender = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'subject' field.
      * @return The value.
      */
    public CharSequence getSubject() {
      return subject;
    }

    /**
      * Sets the value of the 'subject' field.
      * @param value The value of 'subject'.
      * @return This builder.
      */
    public Builder setSubject(CharSequence value) {
      validate(fields()[5], value);
      this.subject = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'subject' field has been set.
      * @return True if the 'subject' field has been set, false otherwise.
      */
    public boolean hasSubject() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'subject' field.
      * @return This builder.
      */
    public Builder clearSubject() {
      subject = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'datas' field.
      * @return The value.
      */
    public java.util.Map<CharSequence, CharSequence> getDatas() {
      return datas;
    }

    /**
      * Sets the value of the 'datas' field.
      * @param value The value of 'datas'.
      * @return This builder.
      */
    public Builder setDatas(java.util.Map<CharSequence, CharSequence> value) {
      validate(fields()[6], value);
      this.datas = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'datas' field has been set.
      * @return True if the 'datas' field has been set, false otherwise.
      */
    public boolean hasDatas() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'datas' field.
      * @return This builder.
      */
    public Builder clearDatas() {
      datas = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MailRecord build() {
      try {
        MailRecord record = new MailRecord();
        record.mailType = fieldSetFlags()[0] ? this.mailType : (CharSequence) defaultValue(fields()[0]);
        record.replyTo = fieldSetFlags()[1] ? this.replyTo : (CharSequence) defaultValue(fields()[1]);
        record.recipients = fieldSetFlags()[2] ? this.recipients : (java.util.List<CharSequence>) defaultValue(fields()[2]);
        record.mailCC = fieldSetFlags()[3] ? this.mailCC : (CharSequence) defaultValue(fields()[3]);
        record.sender = fieldSetFlags()[4] ? this.sender : (CharSequence) defaultValue(fields()[4]);
        record.subject = fieldSetFlags()[5] ? this.subject : (CharSequence) defaultValue(fields()[5]);
        record.datas = fieldSetFlags()[6] ? this.datas : (java.util.Map<CharSequence, CharSequence>) defaultValue(fields()[6]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<MailRecord>
    WRITER$ = (org.apache.avro.io.DatumWriter<MailRecord>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<MailRecord>
    READER$ = (org.apache.avro.io.DatumReader<MailRecord>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
