package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.PromotionTopicService;
import com.xiaosecond.shop.view.PromotionTopicView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 专题 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
@Controller
@RequestMapping("/promotion/topic")
public class PromotionTopicController {

    @Autowired
    private PromotionTopicService promotionTopicService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Response list(PromotionTopicView promotionTopicView , PageParam pageParam) {
        return ResponseUtils.success(promotionTopicService.listPage(promotionTopicView , pageParam));
    }

}
