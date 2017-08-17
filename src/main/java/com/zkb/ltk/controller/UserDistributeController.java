package com.zkb.ltk.controller;

import com.zkb.ltk.service.ImportService;
import com.zkb.ltk.service.UserDistributeService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.SocketException;
import java.util.HashMap;

/**
 * Created by ashley_wsm on 2017/3/7.
 */
@Controller
@RequestMapping("/login/workspace/user")
public class UserDistributeController {
    @Resource(name = "userDistributeService")
    UserDistributeService userDistributeService;

    @Resource(name = "importService")
    ImportService importService;

    @RequestMapping("/two")
    @ResponseBody
    public String getUserDistribute(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String dateStart = request.getParameter("date_start");
        String dateEnd = request.getParameter("date_end");
        String[] array = new String[11];
        array[0] = "山东省";array[1] = "江苏省";array[2] = "广东省";array[3] = "河北省";array[4] = "浙江省";
        array[5] = "北京市";array[6] = "上海市";array[7] = "天津市";array[8] = "安徽省";array[9] = "湖北省";
        array[10] = "其他省";
        String string = userDistributeService.getUser(array,dateStart,dateEnd);

        return string;
    }


    @RequestMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpSession session) throws SocketException {
        //@RequestParam("file") MultipartFile file, 加上了这个 才出错的

        File csvfile = new File("E:\\毕设\\毕设中期\\上传\\0501.csv");
        String success = importService.importcsvfile(csvfile);
        /*CommonsMultipartFile cf= (CommonsMultipartFile)file;
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File f = fi.getStoreLocation();//这个f就是enen*/
        return "";
    }

}
