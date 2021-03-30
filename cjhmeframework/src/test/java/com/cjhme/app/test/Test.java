package com.cjhme.app.test;

import com.cjhme.system.main.util.DesUtil;

import net.sf.json.JSONObject;

public class Test {
    private static String SERVER_ROOT="http://localhost/cjhmeframework/appDispatcher/doPostReq.jhtml";
	public static void main(String[] args) {
		ReuquestBean bean=new ReuquestBean();
		bean.getRequestHeader().put("actId", "aa");
		
		String encryptStr=DesUtil.encrypt(JSONObject.fromObject(bean).toString(), "app");
		
		JSONObject requestJson=new JSONObject();
		requestJson.put("param", encryptStr);
		String result=SendPostReq.sendPost(SERVER_ROOT,requestJson.toString());
		System.out.println(result);
	}
}
