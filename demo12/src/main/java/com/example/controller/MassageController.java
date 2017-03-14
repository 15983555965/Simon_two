package com.example.controller;

import com.example.config.RongYunConfig;
import com.example.repository.UserRepository;
import com.example.three_sdk.rong.RongCloud;
import com.example.three_sdk.rong.messages.TxtMessage;
import com.example.three_sdk.rong.models.CodeSuccessReslut;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 消息的发送
 * Created by Administrator on 2017/3/11.
 */
@RestController
public class MassageController {

    private UserRepository userRepository;
    private RongCloud rongCloud;

    public MassageController(UserRepository userRepository) {
        this.userRepository = userRepository;
        initData();
    }

    private void initData() {
        rongCloud = RongCloud.getInstance(RongYunConfig.appKey, RongYunConfig.appSecret);
    }

    @RequestMapping("/sendSingleMessageText")
    public CodeSuccessReslut sendSingleMessageText(@RequestParam long fromUser, @RequestParam long toUser, @RequestParam String content) throws Exception {
        String[] messagePublishPrivateToUserId = {toUser+""};
        TxtMessage txtMessage = new TxtMessage(content, "helloExtra");
        CodeSuccessReslut messagePublishPrivateResult = rongCloud.message.publishPrivate(fromUser+"", messagePublishPrivateToUserId, txtMessage, "thisisapush", "{\"pushData\":\"hello\"}", "4", 0, 0, 0, 0);
//        if (messagePublishPrivateResult.getCode()!= BaseEntity.CODE_200){
//            return HttpResultUtils.createResultByCode(BaseEntity.CODE_3301);
//        }
        return messagePublishPrivateResult;
    }
}
