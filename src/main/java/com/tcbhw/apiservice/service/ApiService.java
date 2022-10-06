package com.tcbhw.apiservice.service;

import com.tcbhw.apiservice.entity.PoolEntity;
import com.tcbhw.apiservice.entity.QueryEntity;
import com.tcbhw.apiservice.entity.QueryReturnEntity;
import com.tcbhw.apiservice.entity.SavingPoolEntity;
import com.tcbhw.constant.CommonConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ApiService implements ApiInterface {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    public ApiService() {}

    public static final ConcurrentHashMap<Long, PoolEntity> map = new ConcurrentHashMap<>();

    /**
     * This method return status of the request, if poolId is in application then return appended
     * else it will create new element and return inserted status
     * @param poolEntity
     * @return
     */
    @Override
    public String createPool(PoolEntity poolEntity) {
        SavingPoolEntity savingPushEntity = new SavingPoolEntity();
        PoolEntity existedEntity = map.get(poolEntity.getPoolId());
        if (null == existedEntity) {
            savingPushEntity.setPoolId(poolEntity.getPoolId());
            savingPushEntity.setPoolValues(poolEntity.getPoolValues());
            map.put(savingPushEntity.getPoolId(), savingPushEntity);
            return CommonConstants.INSERTED;
        } else {
            Long[] newPools = ArrayUtils.addAll(existedEntity.getPoolValues(), poolEntity.getPoolValues());
            savingPushEntity.setPoolId(existedEntity.getPoolId());
            savingPushEntity.setPoolValues(newPools);
            map.put(savingPushEntity.getPoolId(), savingPushEntity);
            return CommonConstants.APPENDED;
        }
    }

    /**
     * Get input from Controller and return percentage of sum of all elements in pool
     * And number of elements in pool
     * @param queryEntity
     * @return
     */
    @Override
    public QueryReturnEntity query(QueryEntity queryEntity) {
        //Check if id pool is existed
        PoolEntity  poolEntity  = map.get(queryEntity.getPoolId());
        if (null != poolEntity) {

            //Calculate with provided quantile by api
            Long[] sortedPool = poolEntity.getPoolValues();
            Arrays.sort(sortedPool);
            long calculatedQuantile = percentile(sortedPool, queryEntity.getPercentile());
            long elementCount = sortedPool.length;
            return new QueryReturnEntity(calculatedQuantile, elementCount);
        }
        return null;
    }

    /**
     * This method return info of specific pool with provided poolId
     * @param poolId
     * @return
     */
    @Override
    public PoolEntity getPool(long poolId) {
        PoolEntity poolEntity = map.get(poolId);
        if (null != poolEntity) {
            Long[] sortedPool = poolEntity.getPoolValues();
            Arrays.sort(sortedPool);
            return new PoolEntity(poolEntity.getPoolId(), sortedPool);
        }
        return null;
    }

    /**
     * This method calculate the quantile with percentile
     * @param pool
     * @param percentile
     * @return
     */
    public static long percentile(Long[] pool, double percentile) {
        int index = (int) Math.ceil(percentile / 100.0 * pool.length);
        return pool[index - 1];
    }
}
