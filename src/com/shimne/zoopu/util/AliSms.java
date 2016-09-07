package com.shimne.zoopu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class AliSms
{
	public static void main(String[] args)
	{
		String string = "{\"alibaba_aliqin_fc_sms_num_send_response\":{\"result\":{\"err_code\":\"0\",\"model\":\"101061804163^1101538667128\",\"success\":true},\"request_id\":\"13yso3ooc6xj0\"}}";
		JSONObject jsonObject = JSON.parseObject(string);
//		String r = jsonObject.getString("alibaba_aliqin_fc_sms_num_send_response");
//		JSONObject rJsonObject =  JSON.parseObject(r);
//		String r1 = rJsonObject.getString("result");
		JSONObject eJsonObject =  jsonObject.getJSONObject("alibaba_aliqin_fc_sms_num_send_response").getJSONObject("result");
		System.out.println(eJsonObject.get("err_code"));
	}

//	public static void main(String[] args) throws ApiException
//	{
//		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23333375", "5ba86e7c7cc1b854945a98234a04de44");
//		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend("123456");
//		req.setSmsType("normal");
//		req.setSmsFreeSignName("×¢²áÑéÖ¤");
//		req.setSmsParam("{'code':'1234','product':'µÎµÎ°ï'}");
//		req.setRecNum("18263303553");
//		req.setSmsTemplateCode("SMS_5455130");
//		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
//		System.out.println(rsp.getBody());
//	}
}