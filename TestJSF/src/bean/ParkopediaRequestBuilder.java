package bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@ManagedBean
public class ParkopediaRequestBuilder {
    public static final String CLIENT_ID = "oneiotworld_26e99";
    public static final String PASSWORD = "DanGZb7JAOvQVUy1";
    private static final String PARKOPEDIA_URI = "/api/search";
    private static final String SEARCH_URL = "http://a2.parkopedia.cn/api/search/?";

    private StringBuffer mPlainTextParameters;
    private StringBuffer mEncodedParameters;
    private String fullURL;
    private String currentLng;
    private String currentLat;
    private String selectedFormat;
    private String locale;
    private String structured;
    private String sortord;
    private String email;
    private String image_url;
    private String spaceAdditionalInfo;
    private String result_string;
    private JSONObject result;
    private Date startDateTime;
    private Date endDateTime;
    private String dates = "";
    
	public void buildRequest() {
    	this.buildRequest("", Double.parseDouble(getCurrentLat()), Double.parseDouble(getCurrentLng()));
    }
    // Return text string of parameters for POSTing
    public String buildRequest(String query, double latitude, double longitude) {
        mPlainTextParameters = new StringBuffer("");

        // Prefix the encoded parameters string with the password plus a "_path" parameter containing the URI;
        mEncodedParameters = new StringBuffer(PASSWORD + "_path");
        mEncodedParameters.append(PARKOPEDIA_URI);

        // These must be in alphabetical order for the authentication to work.
        appendParameter("apiver", "7");
        appendParameter("cid", CLIENT_ID);
        getDates();
        if (!dates.equals("")) {
        	appendParameter("dates", getDates());
		}
        appendParameter("dev", getDeviceIdentifier());
        appendParameter("fmt", getSelectedFormat());
        appendParameter("lang", getLocale());
        appendParameter("lat", "" + latitude);
        appendParameter("lng", "" + longitude);
//        appendParameter("m", "");
        appendParameter("mt", "0");
        appendParameter("osver", "" + getOSVersion());
        appendParameter("q", query);
        if (sortord != null && !sortord.equals("")) {
        	appendParameter("sort", getSortord());
        }
        appendParameter("stringrefs", "1");
        if (structured.equals("1")) {
        	appendParameter("structured", "1");
		}
        appendParameter("u", getUUID());
        appendParameter("v", getAppVersion());

        // generate signature and append to request params
        System.out.println(mEncodedParameters.toString());
        String sig = md5(mEncodedParameters.toString());
        mPlainTextParameters.append("&sig=").append(URLEncode(sig));
        System.out.println(mPlainTextParameters.toString());
        setFullURL(SEARCH_URL + mPlainTextParameters.toString());
        System.out.println("Full URL: " + this.fullURL);
        handleData();

        return mPlainTextParameters.toString();
    }
    
    public void resetValues() {
    	setSelectedFormat("json");
    	setStructured("1");
    	setLocale("en");
    	setSortord("");
    	setCurrentLat("");
    	setCurrentLng("");
    	setEmail("");
    	setImage_url("");
    	setStartDateTime(null);
    	setEndDateTime(null);
    	setSpaceAdditionalInfo("");
    }

    /**
	 * @description 
	 * @author Danny
	 * @date 2016年6月27日 下午5:41:39
	 * @version 
	 */ 
	private String getDates() {
		if (getStartDateTime()!=null && getEndDateTime()!=null) {
			SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmm");
			String startDateFormatted = format.format(getStartDateTime());
			String endDateFormatted = format.format(getEndDateTime());
			if ((startDateFormatted ==null || startDateFormatted.equals("")) && (endDateFormatted ==null || endDateFormatted.equals(""))) {
				return this.dates;
			}
			this.dates = startDateFormatted + "-" + endDateFormatted;
		}
		return this.dates;
	}
	protected String getDeviceIdentifier() {
    	return "iPhone3GS";
	}
    public String getLocale() {
    	return locale;
	}
    public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getSortord() {
		return sortord;
	}
	public void setSortord(String sortord) {
		this.sortord = sortord;
	}
	public String getStructured() {
		return structured;
	}
	public void setStructured(String structured) {
		this.structured = structured;
	}
	protected String getOSVersion() {
    	return "4.2.1";
	}
    protected String getUUID() {
    	return "71D26213-D595-4355-A1EB-5627049C5632";
	}
    protected String getAppVersion() {
    	return "1.6";
	}
    
    private void appendParameter(String key, String value) {
        try {
            String urlEncoded = URLEncode(value);
            String hash = urlEncoded;
            if (urlEncoded.length() > 32) {
                hash = md5(urlEncoded);
            }
            if (mPlainTextParameters.length() > 0) {
                mPlainTextParameters.append('&');
            }
            mPlainTextParameters.append(key).append('=').append(urlEncoded);
            mEncodedParameters.append(key).append(hash);
        } catch (Exception e) {
            // handle error
        }
    }

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getSpaceAdditionalInfo() {
		return spaceAdditionalInfo;
	}
	public void setSpaceAdditionalInfo(String spaceAdditionalInfo) {
		this.spaceAdditionalInfo = spaceAdditionalInfo;
	}
	public String getFullURL() {
		return fullURL;
	}
	public void setFullURL(String fullURL) {
		this.fullURL = fullURL;
	}
	public String getCurrentLng() {
		return currentLng;
	}

	public void setCurrentLng(String currentLng) {
		this.currentLng = currentLng;
	}

	public String getCurrentLat() {
		return currentLat;
	}

	public void setCurrentLat(String currentLat) {
		this.currentLat = currentLat;
	}

	public String getSelectedFormat() {
		return selectedFormat;
	}
	public void setSelectedFormat(String selectedFormat) {
		this.selectedFormat = selectedFormat;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	private String md5(String plainText) {
        StringBuffer hashText = new StringBuffer("");
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plainText.getBytes());
            byte[] digest = m.digest();
            // Pad it with '0' at the beginning if less than 32 chars.
            BigInteger bigInt = new BigInteger(1, digest);
            hashText.append(bigInt.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, '0');
            }
        } catch (Exception e) {
            System.out.println("md5: " + e);
        }
        return hashText.toString();
    }

    private String URLEncode(String value) {
        // URLEncoder.encode() will covert spaces into '+' instead of "%20" therefore
        // we must make some changes to comply with RFC3986.
        try {
            value = URLEncoder.encode(value, "UTF-8");
            value = value.replace("+", "%20");
            value = value.replace("*", "%2A");
            value = value.replace("~", "%7E");
        } catch (Exception e) {
            // handle error
        }

        return value;
    }
    
    public JSONObject handleData() {
    	// Make a URL to the web page
        URL url;
		try {
			url = new URL(getFullURL());
			// Get the input stream through URL Connection
			URLConnection con = url.openConnection();
			InputStream is =con.getInputStream();
			
			// Once you have the Input Stream, it's just plain old Java IO stuff.
			
			// For this case, since you are interested in getting plain-text web page
			// I'll use a reader and output the text content to System.out.
			
			// For binary content, it's better to directly read the bytes from stream and write
			// to the target file.
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String line = null;
			
			JSONArray returnJsonArray=new JSONArray();
			
			// read each line and write to System.out
			while ((line = br.readLine()) != null) {
				setResult_string(line);
				this.result = JSONObject.parseObject(line);
				System.out.println(line);
			}
			if(jsonObjectIsNotEmpty(result) && result.containsKey("result")){
				String json= result.getString("result");
				if(json.startsWith("{")){
					JSONObject jObject=result.getJSONObject("result");
					if(jObject.containsKey("spaces")){
						returnJsonArray.addAll(jObject.getJSONArray("spaces"));
						System.out.println();
					}
				}
			}
			br.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

    }
    
    public static boolean jsonObjectIsNotEmpty(JSONObject jsonObject) {
		try {
			if(null!=jsonObject && !"".equals(jsonObject) && !jsonObject.isEmpty() && 0<jsonObject.size()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
    
    public String getResult_string() {
		return result_string;
	}
	public void setResult_string(String result_string) {
		this.result_string = result_string;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	public static void main(String[] args) {
		ParkopediaRequestBuilder park = new ParkopediaRequestBuilder();
		park.buildRequest();
	}
}

