package com.flink.ni.demo.checkpoint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskCheckpointStatistics {
    public static final String FIELD_NAME_ID = "id";

    public static final String FIELD_NAME_CHECKPOINT_STATUS = "status";

    public static final String FIELD_NAME_LATEST_ACK_TIMESTAMP = "latest_ack_timestamp";

    public static final String FIELD_NAME_CHECKPOINTED_SIZE = "checkpointed_size";

    /**
     * The accurate name of this field should be 'checkpointed_data_size', keep it as before to not
     * break backwards compatibility for old web UI.
     *
     * @see <a href="https://issues.apache.org/jira/browse/FLINK-13390">FLINK-13390</a>
     */
    public static final String FIELD_NAME_STATE_SIZE = "state_size";

    public static final String FIELD_NAME_DURATION = "end_to_end_duration";

    public static final String FIELD_NAME_ALIGNMENT_BUFFERED = "alignment_buffered";

    public static final String FIELD_NAME_PROCESSED_DATA = "processed_data";

    public static final String FIELD_NAME_PERSISTED_DATA = "persisted_data";

    public static final String FIELD_NAME_NUM_SUBTASKS = "num_subtasks";

    public static final String FIELD_NAME_NUM_ACK_SUBTASKS = "num_acknowledged_subtasks";

    @JsonProperty(FIELD_NAME_ID)
    private long checkpointId;

    @JsonProperty(FIELD_NAME_CHECKPOINT_STATUS)
    private String checkpointStatus;

    @JsonProperty(FIELD_NAME_LATEST_ACK_TIMESTAMP)
    private long latestAckTimestamp;

    @JsonProperty(FIELD_NAME_CHECKPOINTED_SIZE)
    private long checkpointedSize;

    @JsonProperty(FIELD_NAME_STATE_SIZE)
    private long stateSize;

    @JsonProperty(FIELD_NAME_DURATION)
    private long duration;

    @JsonProperty(FIELD_NAME_ALIGNMENT_BUFFERED)
    private long alignmentBuffered;

    @JsonProperty(FIELD_NAME_PROCESSED_DATA)
    private long processedData;

    @JsonProperty(FIELD_NAME_PERSISTED_DATA)
    private long persistedData;

    @JsonProperty(FIELD_NAME_NUM_SUBTASKS)
    private int numSubtasks;

    @JsonProperty(FIELD_NAME_NUM_ACK_SUBTASKS)
    private int numAckSubtasks;

    @JsonCreator
    public TaskCheckpointStatistics(
            @JsonProperty(FIELD_NAME_ID) long checkpointId,
            @JsonProperty(FIELD_NAME_CHECKPOINT_STATUS) String checkpointStatus,
            @JsonProperty(FIELD_NAME_LATEST_ACK_TIMESTAMP) long latestAckTimestamp,
            @JsonProperty(FIELD_NAME_CHECKPOINTED_SIZE) long checkpointedSize,
            @JsonProperty(FIELD_NAME_STATE_SIZE) long stateSize,
            @JsonProperty(FIELD_NAME_DURATION) long duration,
            @JsonProperty(FIELD_NAME_ALIGNMENT_BUFFERED) long alignmentBuffered,
            @JsonProperty(FIELD_NAME_PROCESSED_DATA) long processedData,
            @JsonProperty(FIELD_NAME_PERSISTED_DATA) long persistedData,
            @JsonProperty(FIELD_NAME_NUM_SUBTASKS) int numSubtasks,
            @JsonProperty(FIELD_NAME_NUM_ACK_SUBTASKS) int numAckSubtasks) {

        this.checkpointId = checkpointId;
        this.checkpointStatus = Preconditions.checkNotNull(checkpointStatus);
        this.latestAckTimestamp = latestAckTimestamp;
        this.checkpointedSize = checkpointedSize;
        this.stateSize = stateSize;
        this.duration = duration;
        this.processedData = processedData;
        this.alignmentBuffered = alignmentBuffered;
        this.persistedData = persistedData;
        this.numSubtasks = numSubtasks;
        this.numAckSubtasks = numAckSubtasks;
    }
}
