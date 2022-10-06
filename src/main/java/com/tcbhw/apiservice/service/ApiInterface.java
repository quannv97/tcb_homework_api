package com.tcbhw.apiservice.service;

import com.tcbhw.apiservice.entity.PoolEntity;
import com.tcbhw.apiservice.entity.QueryEntity;
import com.tcbhw.apiservice.entity.QueryReturnEntity;

public interface ApiInterface {
    String createPool(PoolEntity pushEntity);
    QueryReturnEntity query(QueryEntity queryEntity);
    PoolEntity getPool(long poolId);
}
