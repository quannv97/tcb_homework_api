package com.tcbhw.apiservice.service;

import com.tcbhw.apiservice.entity.PoolEntity;
import com.tcbhw.apiservice.entity.QueryEntity;
import com.tcbhw.apiservice.entity.QueryReturnEntity;
import com.tcbhw.constant.CommonConstants;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiServiceTest {

    @Autowired
    ApiInterface apiInterface;

    //1st call createPool
    @Test
    void createPoolTest1() {
        //Create mock data
        Long poolId = 1L;
        Long[] poolValues = {1L, 2L};
        PoolEntity poolEntity = new PoolEntity();
        poolEntity.setPoolId(poolId);
        poolEntity.setPoolValues(poolValues);

        //call service first time and expect result is "INSERTED"
        String result1 = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.INSERTED, result1);
    }

    //2nd call createPool
    @Test
    void createPoolTest2() {
        //Create mock data
        Long poolId1 = 2L;
        Long poolId2 = 3L;
        Long poolId3 = 4L;
        Long[] poolValues1 = {1L, 2L};
        Long[] poolValues2 = {2L, 3L};
        Long[] poolValues3 = {4L, 5L};
        PoolEntity poolEntity = new PoolEntity();
        poolEntity.setPoolId(poolId1);
        poolEntity.setPoolValues(poolValues1);

        //call service many time with other poolId and expect result is "INSERTED"
        String result1 = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.INSERTED, result1);
        poolEntity.setPoolId(poolId2);
        poolEntity.setPoolValues(poolValues2);
        String result2 = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.INSERTED, result2);
        poolEntity.setPoolId(poolId3);
        poolEntity.setPoolValues(poolValues3);
        String result3 = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.INSERTED, result3);
    }

    //3rd call createPool
    @Test
    void createPoolTest3() {
        //Create mock data
        Long poolId = 1L;
        Long[] poolValues1 = {3L, 4L, 5L};
        PoolEntity poolEntity = new PoolEntity();
        poolEntity.setPoolId(poolId);
        poolEntity.setPoolValues(poolValues1);

        //call service expect result is "APPENDED"
        String result = apiInterface.createPool(poolEntity);
        Assert.assertEquals(CommonConstants.APPENDED, result);
    }

    @Test
    void query() {
        //Create mock data
        Long poolId = 5L;
        Long[] poolValues = {1L, 2L, 3L, 4L, 5L};
        Double percent = 50.0;
        QueryEntity queryEntity = new QueryEntity();
        queryEntity.setPoolId(poolId);
        queryEntity.setPercentile(percent);

        //createPool
        PoolEntity poolEntity = new PoolEntity(poolId, poolValues);
        apiInterface.createPool(poolEntity);

        //call service query with poolId = 5 and poolValues [1,2,3,4,5], percentile=50% expect result is "3"
        QueryReturnEntity queryReturnEntity = apiInterface.query(queryEntity);
        Assert.assertEquals(3, queryReturnEntity.getCalculatedQuantile());

        //call service query with poolId = 6 and poolValues [1,2,3,4,5], percentile=50% expect result "Pool is not existed!"
        queryEntity.setPoolId(6L);
        QueryReturnEntity queryReturnEntityNew = apiInterface.query(queryEntity);
        Assert.assertEquals(null, queryReturnEntityNew);
    }

    @Test
    void getPool() {

        //Create mock data
        Long poolId1 = 1L;
        Long poolId2 = 2L;
        Long[] poolValues1 = {1L, 2L, 3L, 4L, 5L};
        Long[] poolValues2 = {1L, 2L, 5L};

        //createPool
        PoolEntity poolEntity1 = new PoolEntity(poolId1, poolValues1);
        apiInterface.createPool(poolEntity1);
        PoolEntity poolEntity2 = new PoolEntity(poolId2, poolValues2);
        apiInterface.createPool(poolEntity2);

        //call service with poolId = 1 and 2 expect PoolEntity not null
        PoolEntity resultPoolEntity1 = apiInterface.getPool(1L);
        Assert.assertEquals(1, resultPoolEntity1.getPoolId());
        PoolEntity resultPoolEntity2 = apiInterface.getPool(2L);
        Assert.assertEquals(2, resultPoolEntity2.getPoolId());

        //call service with poolId = 10 expect PoolEntity is null
        PoolEntity poolEntity10 = apiInterface.getPool(10L);
        Assert.assertEquals(null, poolEntity10);
    }

    @Test
    void percentile() {
    }
}