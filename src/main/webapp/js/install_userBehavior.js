/**
 * Created by acer on 2015/4/13.
 */
(function(window,document,undefined){
    /*安装采集脚本的js程序*/
    // upLogger对象是采集脚本对外提供的操作对象
    if (window.upLogger){//如果不为空，直接返回，避免重复安装
        return;
    }
    var cookieUtil = {//cookie操作工具类
        setCookie:function(sName,sValue,oExpires,sPath,sDomain,bSecure){
            var currDate = new Date(),
                sExpires = typeof oExpires == 'undefined'?'':';expires=' + new Date(currDate.getTime() + (oExpires * 24 * 60 * 60* 1000)).toUTCString();
            document.cookie = sName + '=' + sValue + sExpires + ((sPath == null)?'':(' ;path=' + sPath)) + ((sDomain == null)?'':(' ;domain=' + sDomain)) + ((bSecure == true)?' ; secure':'');
        },
        getCookie:function(sName){
            var regRes = document.cookie.match(new RegExp("(^| )" + sName + "=([^;]*)(;|$)"));
            return (regRes != null)?unescape(regRes[2]):'-';
        }
    };
    var btsVal = cookieUtil.getCookie('b_t_s'),//b_t_s的cookie作用1.标识该页面是否已经安装了采集脚本；2.记录采集脚本的有效期
        startTime = 0,
        intervalTime = 3 * 24 * 60 * 60 * 1000,
        currIntervalTime = new Date().getTime() - 1200000000000,
        domainHead = (document.URL.substring(0,document.URL.indexOf('://'))) + '://';
    if (btsVal != '-' && btsVal.indexOf('t') != -1){
        var getBtsTime = btsVal.substring(btsVal.indexOf('t') + 1,btsVal.indexOf('x'));
        getCurrInterVal = currIntervalTime - getBtsTime;
        if (getCurrInterVal > intervalTime){
            startTime = currIntervalTime;
            cookieUtil.setCookie('b_t_s',btsVal.replace('t' + getBtsTime + 'x', 't' + currIntervalTime + 'x'), 10000, '/');
        }else{
            startTime = getBtsTime;
        }
    }else{
        if (btsVal == '-'){
            cookieUtil.setCookie('b_t_s','t' + currIntervalTime + 'x', 10000, '/');
        }else{
            cookieUtil.setCookie('b_t_s',btsVal + 't' + currIntervalTime + 'x', 10000, '/');
        }
        startTime = currIntervalTime;
    }
    document.write('<script src="' + domainHead + '127.0.0.1:8083/js/userBehavior.js?' + startTime + '"><\/script>');//安装采集脚本
})(window,document);
