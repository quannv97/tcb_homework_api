package com.tcbhw.apiservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * This entity holds info which are provided by user by using POST method: .../push
 * Furthermore, it includes some pre-check condition of each property
 */
public class PoolEntity implements Serializable {

    @NotNull(message = "poolId is mandatory")
    @Min(value = 1, message = "must be equal or greater than 1")
    @Max(value = Integer.MAX_VALUE, message = "must be equal or less than max values of Integer")
    @JsonProperty("poolId")
    private Long poolId;

    @NotNull(message = "poolValues is mandatory")
    @Size(min = 1)
    @JsonProperty("poolValues")
    private Long[] poolValues;

    public PoolEntity() {
    }

    public PoolEntity(Long poolId, Long[] poolValues) {
        this.poolId = poolId;
        this.poolValues = poolValues;
    }

    public long getPoolId() {
        return poolId;
    }

    public void setPoolId(Long poolId) {
        this.poolId = poolId;
    }

    public Long[] getPoolValues() {
        return poolValues;
    }

    public void setPoolValues(Long[] poolValues) {
        this.poolValues = poolValues;
    }
}
