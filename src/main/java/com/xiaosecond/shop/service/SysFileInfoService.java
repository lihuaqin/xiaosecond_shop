package com.xiaosecond.shop.service;

import com.xiaosecond.shop.vo.SysFileInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 文件 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
public interface SysFileInfoService extends IService<SysFileInfoVo> {

    SysFileInfoVo upload(MultipartFile multipartFile);

    void downloadFile(String fileName, Long idFile);

    void getImgStream(String  fileName , HttpServletResponse response);

}
