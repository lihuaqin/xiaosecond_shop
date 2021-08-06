package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.cache.Cache;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.concurrent.TimeUnit;

@Slf4j
@Api(tags = "缓存控制")
@Controller
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private Cache cache;

    @ApiOperation("添加缓存-- 失效时间timeout为空时 默认为1天，单位为秒")
    @RequestMapping(value = "/add" ,method = RequestMethod.POST )
    @ResponseBody
    public Response add(@Param("key") String key , @Param("value")String value , @Param("timeout")Long timeout) {
        if(timeout != null){
            cache.set(key , value , timeout , TimeUnit.SECONDS);
        }else{
            cache.set(key , value);
        }
        return ResponseUtils.success();
    }


    @ApiOperation("查询缓存")
    @RequestMapping(value = "/get" ,method = RequestMethod.GET )
    @ResponseBody
    public Response get(String key){
        return ResponseUtils.success(cache.get(key));
    }

    @ApiOperation("查询所有")
    @RequestMapping(value = "/getAll" ,method = RequestMethod.GET )
    @ResponseBody
    public Response getAll( ){
        return ResponseUtils.success(cache.getAll());
    }

    @ApiOperation("删除缓存")
    @RequestMapping(value = "/del" ,method = RequestMethod.DELETE )
    @ResponseBody
    public Response del(String key){
        cache.del(key);
        return ResponseUtils.success();
    }

    @ApiOperation("删除缓存 -所有")
    @RequestMapping(value = "/delAll" ,method = RequestMethod.DELETE )
    @ResponseBody
    public Response delAll(){
        cache.delAll();
        return ResponseUtils.success();
    }


}
