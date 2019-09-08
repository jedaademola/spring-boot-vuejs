package com.hbe.ms.common.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hbe.ms.common.vo.InterfaceTransDetails;
import com.hbe.ms.common.vo.Response;
import com.hbe.ms.common.vo.VlpServResultRequest;

@Service
public class AsyncCommonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncCommonService.class);

    @Autowired
    private RestCommonService restCommonService;

    @Async
    public CompletableFuture<Response> logServerResult(VlpServResultRequest log, String token) {
        LOGGER.error("in AsyncCommonService.logServerResultAsync");
        // Start the clock
        long start = System.currentTimeMillis();
        Response response = null;
        try {
            response = restCommonService.logServerResult(log, token);
        } catch (Exception e) {
            String msg = "Exception in AsyncCommonService.logServerResultAsync";
            LOGGER.error(msg, e);
        }
        LOGGER.error("Elapsed time for in AsyncCommonService.logServerResultAsync:" + (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(response);
    }

    @Async
    public CompletableFuture<Response> logInterfaceTransDetail(InterfaceTransDetails log, String token) {
        LOGGER.error("in AsyncCommonService.logInterfaceTransDetailAsync");
        // Start the clock
        long start = System.currentTimeMillis();
        Response response = null;
        try {
            response = restCommonService.logInterfaceTransDetail(log, token);
        } catch (Exception e) {
            String msg = "Exception in AsyncService.logInterfaceTransDetailAsync";
            LOGGER.error(msg, e);
        }
        LOGGER.error("Elapsed time for in AsyncCommonService.logInterfaceTransDetailAsync:" + (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(response);
    }

}
