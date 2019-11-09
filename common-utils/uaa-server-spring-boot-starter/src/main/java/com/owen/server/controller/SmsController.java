package com.owen.server.controller;

import com.owen.common.util.StringUtils;
import com.owen.common.web.Result;
import com.owen.annotation.LogAnnotation;
import com.owen.server.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信提供
 * @author zzg
 * @date 2019/09/01
 */
@RestController
public class SmsController {

    public final static String SYSMSG_LOGIN_PWD_MSG="验证码：{0}，3分钟内有效";

    @Autowired
    private ValidateCodeService validateCodeService;

    @RequestMapping("/sms/send")
    @LogAnnotation(module="auth-server",recordRequestParam=false)
    public Result sendSms(@RequestParam(value = "mobile",required = false) String mobile) {
        String content = SmsController.SYSMSG_LOGIN_PWD_MSG.replace("{0}", StringUtils.generateRamdomNum());
//        SendMsgResult sendMsgResult = MobileMsgConfig.sendMsg(mobile, content);

        String calidateCode = StringUtils.generateRamdomNum();

        // TODO: 2019-08-29 发送短信验证码 每个公司对接不同，自己实现

        validateCodeService.saveImageCode(mobile, calidateCode);
        return  Result.succeed(  calidateCode, "发送成功");
    }

}
