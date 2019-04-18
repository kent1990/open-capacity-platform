package com.open.capacity.item;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.dao.CommonDao;
import com.open.capacity.entity.DeliverPost;
import com.open.capacity.log.monitor.PointUtil;
import com.open.capacity.support.ThirdServiceProp;
/**
 * @create Created by kl 2019年4月2日
 * Content :数据处理Item
 */
@Service
public class DeliverPostProcessorItem implements ItemProcessor<DeliverPost, DeliverPost> {

	Logger logger = LoggerFactory.getLogger(DeliverPostProcessorItem.class);
	@Autowired
	private CommonDao commonDao ;
	@Autowired
	private ThirdServiceProp thirdServiceProp;
	
	
	@Override
	public DeliverPost process(DeliverPost deliverPost) throws Exception {
		logger.info("订单号：【{}】经过处理器 ", deliverPost.getOrderId());

		{
			// ems是否签收
			String resp = this.getEms(deliverPost.getPostId());
			try {
				Map respMap = JSONObject.parseObject(resp, Map.class);
				if ("0000".equals(respMap.get("code"))) {

					Map rep = (Map) respMap.get("rep");
					Map msg = (Map) rep.get("msg");
					List<Map> traces = (List<Map>) msg.get("traces");
					for (Iterator<Map> it = traces.iterator(); it.hasNext();) {
						Map temp = it.next();
						if ("10".equals(temp.get("code"))) { 
							// 已签收
							deliverPost.setIsArrived(1);
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
				
			}
		}

		{
			// 中通是否签收
			String resp = this.getZT(deliverPost.getPostId());
			try {
				Map respMap = JSONObject.parseObject(resp, Map.class);
				if ("0000".equals(respMap.get("code"))) {

					Map rep = (Map) respMap.get("rep");
					Map msg = (Map) rep.get("msg");
					List<Map> data = (List<Map>) msg.get("data");
					
					for (Iterator<Map> it = data.iterator(); it.hasNext();) {
						Map temp = it.next();
						
						List<Map> traces = (List<Map>) temp.get("traces");
						
						for (Iterator<Map> it1 = traces.iterator(); it1.hasNext();) {
							Map tempT = it1.next();
							if ("收件".equals(tempT.get("scanType"))) { 
								// 已签收
								deliverPost.setIsArrived(1);
							}
						}
						
						
					}
					 
					
					 
				}
			} catch (Exception e) {
			}
		}

		return deliverPost;
	}

	public String getEms(String postId) {

		String transid= PointUtil.getRandom() ;
		// JSONObject resultJosn = JSONObject.fromObject(result);
		StringBuffer strbuf = new StringBuffer();
		String jsonOut = "";
		try {
			com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
			obj.put("method", "ems.inland.trace.query");
			obj.put("action", "3th_ems");

			Date date = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			obj.put("prea", sdf.format(date));// 163315236523523

			com.alibaba.fastjson.JSONObject req = new com.alibaba.fastjson.JSONObject();
			obj.put("req", req);
			com.alibaba.fastjson.JSONObject msg = new com.alibaba.fastjson.JSONObject();
			req.put("msg", msg);
			msg.put("mailNo", postId);
			msg.put("authorization", "408a6c32e61d3ad5cb5c4e0cb3d2b089");
			msg.put("timestamp", System.currentTimeMillis());

			// 请求数据
			jsonOut = obj.toString();

			logger.info("EMS请求处理开始: transid=【{}】 ,req=【{}】", transid ,jsonOut);
			
			String	callurl =  commonDao.getHttpUrl("104"); 
			
			int timeOut = 3000;
			URL url = new URL(thirdServiceProp.getUrl() + callurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			byte[] contentbyte = jsonOut.getBytes("UTF-8");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Content-Length", contentbyte.length + "");

			conn.setRequestProperty("Accept-Encoding", "");
			conn.setRequestProperty("Accept", "application/json");
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
			OutputStream out = conn.getOutputStream();
			out.write(contentbyte); // 发送请求报文
			out.flush();
			out.close();

			InputStream in = conn.getInputStream();
			BufferedReader dr = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String text_rsp = null;

			while ((text_rsp = dr.readLine()) != null) {
				strbuf.append(text_rsp);
			}
			in.close();
			logger.info("EMS请求处理结束: transid=【{}】 ,res=【{}】 ",transid, strbuf);
		} catch (Exception e) {
			strbuf.setLength(0);
			strbuf.append("{\"code\":\"8888\",\"detail\":\"失败\"}");
			logger.error(postId + "EMS转发接口报错！！！");
		}
		return strbuf.toString();

	}

	public String getZT(String postId) {
		String transid= PointUtil.getRandom() ;
		// JSONObject resultJosn = JSONObject.fromObject(result);
		StringBuffer strbuf = new StringBuffer();
		String jsonOut = "";
		try {
			com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
			obj.put("method", "api.traceInterfaceNewTraces");
			obj.put("action", "3th_zto");

			Date date = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			obj.put("prea", sdf.format(date));// 163315236523523

			com.alibaba.fastjson.JSONObject req = new com.alibaba.fastjson.JSONObject();
			obj.put("req", req);
			com.alibaba.fastjson.JSONObject msg = new com.alibaba.fastjson.JSONObject();
			req.put("msg", msg);
			msg.put("company_id", "20f74746141c4433a15e7ddd5aade604");
			msg.put("data", Arrays.asList(postId));
			msg.put("msg_type", "NEW_TRACES");

			// 请求数据
			jsonOut = obj.toString();
			logger.info("中通请求处理开始: transid=【{}】 ,req=【{}】 ",transid , jsonOut);
			String	callurl =  commonDao.getHttpUrl("103");  
			//固定token
			callurl =callurl.replace("tokenid", "798d3ed2ebaec83ae608c10207f783d6") ;
			
			int timeOut = 3000;
			URL url = new URL(thirdServiceProp.getUrl() + callurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			byte[] contentbyte = jsonOut.getBytes("UTF-8");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Content-Length", contentbyte.length + "");

			conn.setRequestProperty("Accept-Encoding", "");
			conn.setRequestProperty("Accept", "application/json");
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
			OutputStream out = conn.getOutputStream();
			out.write(contentbyte); // 发送请求报文
			out.flush();
			out.close();

			InputStream in = conn.getInputStream();
			BufferedReader dr = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String text_rsp = null;

			while ((text_rsp = dr.readLine()) != null) {
				strbuf.append(text_rsp);
			}
			in.close();

			logger.info("中通请求处理结束: transid=【{}】 ,res=【{}】 ",transid, strbuf);
		} catch (Exception e) {
			strbuf.setLength(0);
			strbuf.append("{\"code\":\"8888\",\"detail\":\"失败\"}");
			logger.error(postId + "中通转发接口报错！！！");
		}
		return strbuf.toString();

	}

}
