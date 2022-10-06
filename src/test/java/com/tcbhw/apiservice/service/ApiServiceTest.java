package com.tcbhw.apiservice.service;

import com.tcbhw.apiservice.entity.PoolEntity;
import com.tcbhw.constant.CommonConstants;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiServiceTest {

    @Autowired
    ApiInterface apiInterface;

    @Test
    void createPoolInsertedSuccess1() {
        //Create mock data
        Long poolId = 1L;
        Long[] poolValues = {1L, 2L, 3L, 4L, 5L};
        PoolEntity poolEntity = new PoolEntity();
        poolEntity.setPoolId(poolId);
        poolEntity.setPoolValues(poolValues);

        //call service first time and expect result is "INSERTED"
        String result1 = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.INSERTED, result1);
    }

    @Test
    void createPoolInsertedSuccess2() {
        //Create mock data
        Long poolId1 = 1L;
        Long poolId2 = 2L;
        Long poolId3 = 3L;
        Long[] poolValues1 = {1L, 2L};
        Long[] poolValues2 = {2L, 3L};
        Long[] poolValues3 = {4L, 5L};
        PoolEntity poolEntity = new PoolEntity();
        poolEntity.setPoolId(poolId1);
        poolEntity.setPoolValues(poolValues1);

        //call service many time with other poolId and expect result is "INSERTED"
        String result1 = apiInterface.createPool(poolEntity);
        poolEntity.setPoolId(poolId2);
        poolEntity.setPoolValues(poolValues2);
        Assert.assertEquals(CommonConstants.INSERTED, result1);
        String result2 = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.INSERTED, result2);
        poolEntity.setPoolId(poolId3);
        poolEntity.setPoolValues(poolValues3);
        String result3 = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.INSERTED, result3);
    }

    @Test
    void createPoolAppendSuccess() {
        //Create mock data
        Long poolId = 0L;
        Long[] poolValues1 = {1L, 2L};
        Long[] poolValues2 = {3L, 4L, 5L};
        PoolEntity poolEntity = new PoolEntity();
        poolEntity.setPoolId(poolId);
        poolEntity.setPoolValues(poolValues1);

        //call service first time and expect result is "INSERTED"
        String result1 = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.INSERTED, result1);

        //call service second time with the same poolId and expect result is "APPENDED"
        poolEntity.setPoolValues(poolValues2);
        String result2 = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.APPENDED, result2);
    }

    @Test
    void query() {
    }

    @Test
    void getPool() {
    }

    @Test
    void percentile() {
    }
}