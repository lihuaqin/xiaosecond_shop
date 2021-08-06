package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.common.CfgKey;
import com.xiaosecond.shop.service.SysCfgService;
import com.xiaosecond.shop.utils.HttpUtil;
import com.xiaosecond.shop.vo.SysFileInfoVo;
import com.xiaosecond.shop.mapper.SysFileInfoMapper;
import com.xiaosecond.shop.service.SysFileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 文件 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Service
@Slf4j
public class SysFileInfoServiceImpl extends ServiceImpl<SysFileInfoMapper, SysFileInfoVo> implements SysFileInfoService {

    @Autowired
    private SysCfgService sysCfgService;

    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;

    public SysFileInfoVo upload(MultipartFile multipartFile){
        String uuid = UUID.randomUUID().toString();
        String originalFileName = multipartFile.getOriginalFilename();
        String realFileName =   uuid +"."+ originalFileName.split("\\.")[originalFileName.split("\\.").length-1];
        try {
            File file = new File(sysCfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH) + File.separator+realFileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
            return save(multipartFile.getOriginalFilename(),file);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存文件异常",e);
        }
        return null;
    }

    /**
     * 创建文件
     * @param originalFileName
     * @param file
     * @return
     */
    public SysFileInfoVo save(String originalFileName,File file){
            SysFileInfoVo fileInfo = new SysFileInfoVo();
            fileInfo.setOriginalFileName(originalFileName);
            fileInfo.setRealFileName(file.getName());
            sysFileInfoMapper.insert(fileInfo);
            return fileInfo;
    }

    public void downloadFile(String fileName, Long idFile){
        SysFileInfoVo fileInfo = null;
        QueryWrapper<SysFileInfoVo> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(fileName)) {
            query.eq("real_file_name" ,fileName);
            fileInfo = sysFileInfoMapper.selectOne(query);
        }
        if(idFile!=null){
            fileInfo = sysFileInfoMapper.selectById(idFile);
        }
        downloadFile(fileInfo);
    }

    public void downloadFile(SysFileInfoVo fileInfo){
        String fileName = fileInfo.getOriginalFileName();
        HttpServletResponse response = HttpUtil.getResponse();
        response.setContentType("application/x-download");
        try {
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        int length = 1024;
        byte[] buffer = new byte[length];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            File file = new File(sysCfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH)  + File.separator+ fileInfo.getRealFileName());
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while(i != -1){
                os.write(buffer,0,i);
                buffer = new byte[length];
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("download error",e);
        }finally {
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                log.error("close inputstream error", e);
            }
        }
    }


    public void getImgStream(String  fileName ,HttpServletResponse response){
        QueryWrapper<SysFileInfoVo> query = new QueryWrapper<>();
        query.eq("real_file_name" ,fileName);
        SysFileInfoVo fileInfo = sysFileInfoMapper.selectOne(query);
        FileInputStream fis = null;
        response.setContentType("image/"+fileInfo.getRealFileName().split("\\.")[1]);
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(sysCfgService.getCfgValue(CfgKey.SYSTEM_FILE_UPLOAD_PATH)  + File.separator+ fileInfo.getRealFileName());
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            log.error("getImgStream error",e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("close getImgStream error",e);
                }
            }
        }
    }

}
