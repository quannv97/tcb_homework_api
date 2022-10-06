package com.tcbhw.apiservice.controller;

import com.tcbhw.apiservice.entity.PoolEntity;
import com.tcbhw.apiservice.entity.QueryEntity;
import com.tcbhw.apiservice.entity.QueryReturnEntity;
import com.tcbhw.apiservice.service.ApiInterface;
import com.tcbhw.apiservice.service.ApiService;
import com.tcbhw.constant.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
public class ApiController {

    @Autowired
    private ApiInterface apiInterface;

    /**
     * This method accepted JSON data from users as requested format
     * when users want to insert new or append data to application
     * @param poolEntity
     * @return
     */
    @RequestMapping(value = "/createPool", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Object> createPool(@Valid
                                             @RequestBody PoolEntity poolEntity) {
        return ResponseEntity.status(HttpStatus.OK).body(
                Collections.singletonMap(CommonConstants.STATUS, apiInterface.createPool(poolEntity))
        );
    }

    /**
     * This method receives query request from users with requested format
     * when user want to get info from inserted data which added or appended before
     * if poolId is out of known poolIds it will return warning message but still OK status
     * @param queryEntity
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Object> query(@Valid @RequestBody QueryEntity queryEntity) {
        QueryReturnEntity queryReturnEntity = apiInterface.query(queryEntity);
        if (null != queryReturnEntity) {
            return new ResponseEntity<>(queryReturnEntity, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    Collections.singletonMap(CommonConstants.STATUS, "poolId is not existed!")
            );
        }
    }

    /**
     * This method receives poolId and return info with sorted pool element.
     * @param poolId
     * @return
     */
    @RequestMapping(value = "/getPool", method = RequestMethod.GET)
    public ResponseEntity<Object> getPool(@RequestParam long poolId) {
        PoolEntity poolEntity = apiInterface.getPool(poolId);
        if (null != poolEntity) {
            return new ResponseEntity<>(poolEntity, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    Collections.singletonMap(CommonConstants.STATUS, "Pool is not existed!")
            );
        }
    }
}
