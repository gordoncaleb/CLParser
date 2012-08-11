package utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Web {
	
	public static String getPage(String url) {
		
		System.out.println("Getting page " + url);

		String page = "";
		
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();

			HttpGet httpget = new HttpGet(url);
			
			HttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				
				page = new java.util.Scanner(instream).useDelimiter("\\A").next();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return page;

	}

}
