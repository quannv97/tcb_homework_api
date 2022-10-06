package com.tcbhw.apiservice.entity;

import java.io.Serializable;

/**
 * This entity extends from PushEntity and it holds info of Pushed entity but it separate to easy understand
 */
public class SavingPoolEntity extends PoolEntity implements Serializable {

    public SavingPoolEntity() {
        super();
    }

    public SavingPoolEntity(Long poolId, Long[] poolValues) {
        super(poolId, poolValues);
    }

}
