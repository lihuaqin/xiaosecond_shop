package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.Permission;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.SysFileInfoService;
import com.xiaosecond.shop.vo.SysFileInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 * 文件 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Controller
@RequestMapping("/file")
@Slf4j
public class SysFileInfoController {

    @Autowired
    private SysFileInfoService sysFileInfoService;
    /**
     * 上传文件
     * @param multipartFile
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.FILE_UPLOAD})
    @ResponseBody
    public Response upload(@RequestPart("file") MultipartFile multipartFile) {
        return ResponseUtils.success(sysFileInfoService.upload(multipartFile));
    }

    /**
     * 下载文件
     * @param fileName
     * @param fileName
     */
    @RequestMapping(value="download",method = RequestMethod.GET)
    public void download(@RequestParam(value = "fileName",required = false) String fileName,
                         @RequestParam(value = "idFile",required = false) Long idFile){
        sysFileInfoService.downloadFile(fileName , idFile);
    }

    /**
     * 获取图片流
     * @param response
     * @param fileName
     */
    @RequestMapping(value="getImgStream",method = RequestMethod.GET)
    public void getImgStream(HttpServletResponse response,
                             @RequestParam("idFile")String  fileName){
        sysFileInfoService.getImgStream(fileName , response);
    }
}
