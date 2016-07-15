package bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class TestPullingDateFromURL {

    public static void main(String[] args) throws IOException {

        // Make a URL to the web page
        URL url = new URL("http://a2.parkopedia.cn/api/search/?apiver=7&cid=oneiotworld_26e99&d=&dev=iPhone3GS&fmt=json&lang=en&lat=30.661045&lng=104.078272&m=&mt=0&osver=4.2.1&q=&structured=1&u=71D26213-D595-4355-A1EB-5627049C5632&v=1.6&sig=b4b9271da28fe25c3d02485032d71046");

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

        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
        	JSONObject json = JSONObject.parseObject(line);
            System.out.println(line);
        }
    }
}
