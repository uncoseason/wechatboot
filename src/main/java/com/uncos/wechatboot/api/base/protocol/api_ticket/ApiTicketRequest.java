package com.uncos.wechatboot.api.base.protocol.api_ticket;

import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;
import com.uncos.wechatboot.common.ApiTicketType;

/**
 * Created by xuwen on 2016/1/20.
 */
public class ApiTicketRequest extends BaseAccessTokenRequest {

    private ApiTicketType type;

    public ApiTicketType getType() {
        return type;
    }

    public void setType(ApiTicketType type) {
        this.type = type;
    }
}
