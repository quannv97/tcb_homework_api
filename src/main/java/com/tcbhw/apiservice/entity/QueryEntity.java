package com.tcbhw.apiservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This entity holds info which are provided by user by using POST method: .../query
 * Furthermore, it includes some pre-check condition of each property
 */
public class QueryEntity {

    @NotNull(message = "poolId is mandatory")
    @NotNull(message = "poolId is mandatory")
    @Min(value = 1, message = "must be equal or greater than 1")
    @Max(value = Integer.MAX_VALUE, message = "must be equal or less than max values of Integer")
    @JsonProperty("poolId")
    private Long poolId;

    @NotNull(message = "percentile is mandatory")
    @Range(min = 1, max = 99, message = "percentile must be in range 1-99")
    @JsonProperty("percentile")
    private Double percentile;

    public Long getPoolId() {
        return poolId;
    }

    public void setPoolId(Long poolId) {
        this.poolId = poolId;
    }

    public Double getPercentile() {
        return percentile;
    }

    public void setPercentile(Double percentile) {
        this.percentile = percentile;
    }
}
