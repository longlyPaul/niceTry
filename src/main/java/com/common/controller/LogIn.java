package com.common.controller;

import com.common.entity.Demo;
import com.common.entity.LogEntity;
import com.common.entity.TenderMain;
import com.common.entity.TenderProduct;
import com.common.mapper.DemoMapper;
import com.common.utils.MapUtil;
import org.apache.http.client.fluent.Form;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by acer on 2015/4/13.
 */
@Controller
public class LogIn {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    @Resource
    private DemoMapper demoMapper;

    @RequestMapping("/inpage")
    @RequiresPermissions("common:inpage")
    public void recordAction(LogEntity logEntity){
        //logUrl={/localhost:8088/}&
        // logHisRefer={-}&
        // logParams={subIsNew=0}&
        // logQuery={pageId=1429067673718-87418017243061349761351044203083&
        // title=%E9%A6%96%E9%A1%B5%20-%20ul&
        // charset=UTF-8&sr=1366*768}&
        // ver=228917617293&
        // time=1429067673719"
        System.out.print(logEntity.getLogParams());
        System.out.print(logEntity.getLogQuery());
    }

    @RequestMapping("/click")
//    @RequiresPermissions("common:click")
    public void clickAction(){
        //type=MyTest&
        // clickTarget=testClickBtn&
        // pageId=1429067673718-87418017243061349761351044203083&
        // authId=sharpxiajun&
        // ver=228917617293&
        // time=1429067692205"
        Demo demo = demoMapper.selectByPrimaryKey(1);
        System.out.print("kkkk");
        System.out.print("cccc");
    }

    @RequestMapping("/index")
    public String index(){
        return "foreground/index";
    }


    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        Demo demo = demoMapper.selectByPrimaryKey(1);
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
            WebUtils.saveRequest(request);
        }
//        if(getCurrentUser()!=null){
//            return "previous/index";
//        }
        return "backstage/login";
    }


    public static void main(String[] args) throws IOException {
        TenderMain tenderMain = new TenderMain();
        tenderMain.setProjectName("testProject");
        tenderMain.setAgency("agency");
        tenderMain.setProjectArea("china");
        tenderMain.setProjectType("projectType");
        tenderMain.setOrgid("23");
        tenderMain.setBidorgId("12");
        TenderProduct tenderProduct=new TenderProduct();
        tenderProduct.setBrand("brand");
        tenderProduct.setCertificate("certificate");
        tenderProduct.setDeclare("my declare");
        tenderProduct.setIsImport("import");
        tenderProduct.setTenderId(3);
        TenderProduct tenderProduct2=new TenderProduct();
        tenderProduct2.setBrand("brand2");
        tenderProduct2.setCertificate("certificate3");
        tenderProduct2.setDeclare("my declare4");
        tenderProduct2.setIsImport("import5");
        tenderProduct2.setTenderId(6);
        List<TenderProduct> tenderProductList=new LinkedList<TenderProduct>();
        tenderProductList.add(tenderProduct);
        tenderProductList.add(tenderProduct2);
        tenderMain.setTenderProductList(tenderProductList);
//        String url = "http://169.254.160.128:8088/interface/crate/tender";

//        String url = "http://localhost:8080/rest/test";
//        ClientRequest request = new ClientRequest(url);
//        ClientResponse response = null;
//        try {
//            response = request.head();
//            request.body("application/json", tenderMain);
//            response = request.post();
//            System.out.println(response.getStatus());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//          ClientRequest request = new ClientRequest("http://localhost:8080/ex11_1/services/、shop");
//        String relust= org.apache.http.client.fluent.Request.Post(url).bodyForm(parseParameters(MapUtil.ConvertObjToMap(tenderMain)).build()).execute().returnContent().asString();

//        String relust=Request.Post(url).execute().returnContent().asString();
//        Boolean user=JSON.parseObject(response, Boolean.class);http://127.0.0.1:8083/js/userBehavior.js?228917617293
//        Calendar calendar=Calendar.getInstance();
//        calendar.setTimeInMillis(228917617293L);
//        URLEncoder.encode("%E9%A6%96%E9%A1%B5%20-%20ul","utf-8")
//        Date date=calendar.getTime();

//        System.out.print(URLDecoder.decode("%E9%A6%96%E9%A1%B5%20-%20ul", "utf-8"));
    }

    private static Form parseParameters(Map<String, Object> parameters) {
        if (parameters != null && parameters.size() != 0) {
            Form form = Form.form();
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    for (Map.Entry<String, Object> filterEntry : ((Map<String,Object>)entry.getValue()).entrySet()) {
                        form.add("filter['"+filterEntry.getKey()+"']",filterEntry.getValue().toString());
                    }
                }else if(entry.getValue() instanceof List){
                    for(int i=0;i<((List) entry.getValue()).size();i++){
                        Map<String,Object> temp= MapUtil.ConvertObjToMap(((List) entry.getValue()).get(i));
                        for (Map.Entry<String, Object> entry2 : temp.entrySet()) {
                            Object value = entry2.getValue();
                            form.add(entry.getKey()+"["+i+"]."+entry2.getKey(), value instanceof Date ? sdf.format(value) : String.valueOf(value));
                        }
                    }
                }                     else
                {
                    Object value = entry.getValue();
                    form.add(entry.getKey(), value instanceof Date ? sdf.format(value) : String.valueOf(value));
                }
            }
            return form;
        } else {
            return null;
        }
    }
}
