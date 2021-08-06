package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 */
@RequestMapping("/dashboard")
@RestController
public class DashboardController   {
    @Autowired
    private DashboardService dashboardService;
    @RequestMapping(method = RequestMethod.GET)
    public Response get(){
        return ResponseUtils.success(dashboardService.getDashboardData());
    }
}
