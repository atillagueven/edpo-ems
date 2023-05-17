/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package ch.unisg.ems.eventprocessor.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class EntityProductionEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6915197315101710881L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"EntityProductionEvent\",\"namespace\":\"ch.unisg.ems.eventprocessor.model\",\"fields\":[{\"name\":\"pvId\",\"type\":\"string\"},{\"name\":\"timestamp\",\"type\":\"long\"},{\"name\":\"load\",\"type\":\"double\"},{\"name\":\"unitLoad\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<EntityProductionEvent> ENCODER =
      new BinaryMessageEncoder<EntityProductionEvent>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<EntityProductionEvent> DECODER =
      new BinaryMessageDecoder<EntityProductionEvent>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<EntityProductionEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<EntityProductionEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<EntityProductionEvent>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this EntityProductionEvent to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a EntityProductionEvent from a ByteBuffer. */
  public static EntityProductionEvent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence pvId;
  @Deprecated public long timestamp;
  @Deprecated public double load;
  @Deprecated public java.lang.CharSequence unitLoad;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public EntityProductionEvent() {}

  /**
   * All-args constructor.
   * @param pvId The new value for pvId
   * @param timestamp The new value for timestamp
   * @param load The new value for load
   * @param unitLoad The new value for unitLoad
   */
  public EntityProductionEvent(java.lang.CharSequence pvId, java.lang.Long timestamp, java.lang.Double load, java.lang.CharSequence unitLoad) {
    this.pvId = pvId;
    this.timestamp = timestamp;
    this.load = load;
    this.unitLoad = unitLoad;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return pvId;
    case 1: return timestamp;
    case 2: return load;
    case 3: return unitLoad;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: pvId = (java.lang.CharSequence)value$; break;
    case 1: timestamp = (java.lang.Long)value$; break;
    case 2: load = (java.lang.Double)value$; break;
    case 3: unitLoad = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'pvId' field.
   * @return The value of the 'pvId' field.
   */
  public java.lang.CharSequence getPvId() {
    return pvId;
  }

  /**
   * Sets the value of the 'pvId' field.
   * @param value the value to set.
   */
  public void setPvId(java.lang.CharSequence value) {
    this.pvId = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public java.lang.Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.Long value) {
    this.timestamp = value;
  }

  /**
   * Gets the value of the 'load' field.
   * @return The value of the 'load' field.
   */
  public java.lang.Double getLoad() {
    return load;
  }

  /**
   * Sets the value of the 'load' field.
   * @param value the value to set.
   */
  public void setLoad(java.lang.Double value) {
    this.load = value;
  }

  /**
   * Gets the value of the 'unitLoad' field.
   * @return The value of the 'unitLoad' field.
   */
  public java.lang.CharSequence getUnitLoad() {
    return unitLoad;
  }

  /**
   * Sets the value of the 'unitLoad' field.
   * @param value the value to set.
   */
  public void setUnitLoad(java.lang.CharSequence value) {
    this.unitLoad = value;
  }

  /**
   * Creates a new EntityProductionEvent RecordBuilder.
   * @return A new EntityProductionEvent RecordBuilder
   */
  public static ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder newBuilder() {
    return new ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder();
  }

  /**
   * Creates a new EntityProductionEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new EntityProductionEvent RecordBuilder
   */
  public static ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder newBuilder(ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder other) {
    return new ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder(other);
  }

  /**
   * Creates a new EntityProductionEvent RecordBuilder by copying an existing EntityProductionEvent instance.
   * @param other The existing instance to copy.
   * @return A new EntityProductionEvent RecordBuilder
   */
  public static ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder newBuilder(ch.unisg.ems.eventprocessor.model.EntityProductionEvent other) {
    return new ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder(other);
  }

  /**
   * RecordBuilder for EntityProductionEvent instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<EntityProductionEvent>
    implements org.apache.avro.data.RecordBuilder<EntityProductionEvent> {

    private java.lang.CharSequence pvId;
    private long timestamp;
    private double load;
    private java.lang.CharSequence unitLoad;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.pvId)) {
        this.pvId = data().deepCopy(fields()[0].schema(), other.pvId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[1].schema(), other.timestamp);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.load)) {
        this.load = data().deepCopy(fields()[2].schema(), other.load);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.unitLoad)) {
        this.unitLoad = data().deepCopy(fields()[3].schema(), other.unitLoad);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing EntityProductionEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(ch.unisg.ems.eventprocessor.model.EntityProductionEvent other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.pvId)) {
        this.pvId = data().deepCopy(fields()[0].schema(), other.pvId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[1].schema(), other.timestamp);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.load)) {
        this.load = data().deepCopy(fields()[2].schema(), other.load);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.unitLoad)) {
        this.unitLoad = data().deepCopy(fields()[3].schema(), other.unitLoad);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'pvId' field.
      * @return The value.
      */
    public java.lang.CharSequence getPvId() {
      return pvId;
    }

    /**
      * Sets the value of the 'pvId' field.
      * @param value The value of 'pvId'.
      * @return This builder.
      */
    public ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder setPvId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.pvId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'pvId' field has been set.
      * @return True if the 'pvId' field has been set, false otherwise.
      */
    public boolean hasPvId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'pvId' field.
      * @return This builder.
      */
    public ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder clearPvId() {
      pvId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public java.lang.Long getTimestamp() {
      return timestamp;
    }

    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder setTimestamp(long value) {
      validate(fields()[1], value);
      this.timestamp = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder clearTimestamp() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'load' field.
      * @return The value.
      */
    public java.lang.Double getLoad() {
      return load;
    }

    /**
      * Sets the value of the 'load' field.
      * @param value The value of 'load'.
      * @return This builder.
      */
    public ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder setLoad(double value) {
      validate(fields()[2], value);
      this.load = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'load' field has been set.
      * @return True if the 'load' field has been set, false otherwise.
      */
    public boolean hasLoad() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'load' field.
      * @return This builder.
      */
    public ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder clearLoad() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'unitLoad' field.
      * @return The value.
      */
    public java.lang.CharSequence getUnitLoad() {
      return unitLoad;
    }

    /**
      * Sets the value of the 'unitLoad' field.
      * @param value The value of 'unitLoad'.
      * @return This builder.
      */
    public ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder setUnitLoad(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.unitLoad = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'unitLoad' field has been set.
      * @return True if the 'unitLoad' field has been set, false otherwise.
      */
    public boolean hasUnitLoad() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'unitLoad' field.
      * @return This builder.
      */
    public ch.unisg.ems.eventprocessor.model.EntityProductionEvent.Builder clearUnitLoad() {
      unitLoad = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public EntityProductionEvent build() {
      try {
        EntityProductionEvent record = new EntityProductionEvent();
        record.pvId = fieldSetFlags()[0] ? this.pvId : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.timestamp = fieldSetFlags()[1] ? this.timestamp : (java.lang.Long) defaultValue(fields()[1]);
        record.load = fieldSetFlags()[2] ? this.load : (java.lang.Double) defaultValue(fields()[2]);
        record.unitLoad = fieldSetFlags()[3] ? this.unitLoad : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<EntityProductionEvent>
    WRITER$ = (org.apache.avro.io.DatumWriter<EntityProductionEvent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<EntityProductionEvent>
    READER$ = (org.apache.avro.io.DatumReader<EntityProductionEvent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
