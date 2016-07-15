package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 版权：(C) 版权所有 深圳四海万联科技有限公司. 〈简述〉 通过百度获取位置信息
 * 
 * @author tristan
 * @since 2016年2月3日 下午3:16:52
 */
public class GetLocationByBaidu {

	/**
	 * 〈简述〉 根据经度、纬度 逆地址解析
	 * 
	 * @author tristan
	 * @since 2016年2月3日 下午3:20:30
	 * @param latitude
	 *            纬度
	 * 
	 * 
	 * @param longitude
	 *            经度
	 * @return 此经度纬度对应的位置信息
	 **/
	public static String getGeocoder(String latitude, String longitude,String maptype) {
		String returnValue = "";
		//百度地图

		String url = "http://api.map.baidu.com/geocoder/v2/?ak=47hILcGyU2PpBNl9VBieTyyV&location="
				+ latitude + "," + longitude + "&output=json&pois=0";
		System.out.println("baidu转换地址:"+url);
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		try {
			client.executeMethod(postMethod);
			returnValue = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		System.out.println("坐标转地址："+returnValue);
		return returnValue;
	}

	/**
	 * 〈简述〉 根据经度、纬度 转换
	 * 
	 * @author tristan
	 * @since 2016年2月3日 下午3:20:30
	 * @param latitude
	 *            纬度
	 * 
	 * 
	 * @param longitude
	 *            经度
	 * @return 此经度纬度对应百度地图的经纬度
	 **/
	public static String getConvertedLocation(String latitude, String longitude,String maptype) {
		String returnValue = "";
		//百度地图

		String convertUrl = "http://api.map.baidu.com/geoconv/v1/?coords="
				+ longitude + "," + latitude
				+ "&from=1&to=5&ak=47hILcGyU2PpBNl9VBieTyyV";
		HttpClient client = new HttpClient();
		PostMethod convertpostMethod = new PostMethod(convertUrl);
		try {
			client.executeMethod(convertpostMethod);
			returnValue = convertpostMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		System.out.println("gps转地图坐标"+returnValue);
		return returnValue;
	}
	
	/**
	 * 〈简述〉将收到的逆地理编码高德json数据格式转换成统一的json数据格式
	 * @author cloud
	 * @param String
	 * @return String
	 */
	public static String toFormatGeocodeString(String json){
		String returnStr = "";
		JSONObject jsonobject = JSONObject.parseObject(json);
		if (null != jsonobject && jsonobject.containsKey("regeocode")) {
			JSONObject jsontemp = new JSONObject();
			jsontemp.put("result", jsonobject.getJSONObject("regeocode"));
			jsontemp.put("status", 0);
			returnStr = jsontemp.toJSONString();
		}else{
			returnStr = "";
		}
		return returnStr;
	}
	
	/**
	 * 〈简述〉将收到的逆地理编码高德json数据格式转换成统一的json数据格式
	 * @author cloud
	 * @param String
	 * @return String
	 */
	public static String toFormatConvertedString(String json){
		String returnStr = "";
		JSONObject jsonobject = JSONObject.parseObject(json);
		if (null != jsonobject && jsonobject.containsKey("locations")) {
			JSONObject jsontemp = new JSONObject();
			JSONArray jsonAry = new JSONArray();
			String locations = jsonobject.getString("locations");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("x", locations.split(",")[0]);
			map.put("y", locations.split(",")[1]);
			List<Object> list = new ArrayList<Object>();
			list.add(map);
			jsonAry.add(map);
			jsontemp.put("result", jsonAry);
			jsontemp.put("status", 0);
			returnStr = jsontemp.toJSONString();
		}else{
			returnStr = "";
		}
		return returnStr;
	}

	/*
	 * 〈简述〉
	 * 
	 * @author tristan
	 * 
	 * @since 2016年2月3日 下午3:30:30
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		String latitude = "30.66332100";
		String longitude = "104.08482400";
		String tempString1 = getGeocoder(latitude, longitude,"1");
		System.out.println("转换前地址为：" + tempString1);
		JSONObject json1 = JSONObject.parseObject(tempString1);
		System.out.println("转换前地址："
				+ json1.getJSONObject("result").getString("formatted_address"));
		String temp = getConvertedLocation(latitude, longitude,"1");
		System.out.println("转换后的经纬度：" + temp);
		JSONObject jsonobject = JSONObject.parseObject(temp);
		latitude = jsonobject.getJSONArray("result").getJSONObject(0)
				.getString("y");
		longitude = jsonobject.getJSONArray("result").getJSONObject(0)
				.getString("x");
		String tempString = getGeocoder(latitude, longitude,"1");
		System.out.println("转换后地址为：" + tempString);
		JSONObject json = JSONObject.parseObject(tempString);
		System.out.println("转换后地址："
				+ json.getJSONObject("result").getString("formatted_address"));
	}
}
