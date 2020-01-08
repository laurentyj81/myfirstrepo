package test.ljo.framadate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostFramadate {
	public static void main(String... args) throws IOException {

		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			List<NameValuePair> form = new ArrayList<>();

			form.add(new BasicNameValuePair("control", "fd2f9d31bc4f94452535484179ac80d6"));
			form.add(new BasicNameValuePair("name", "Nowilechat"));
			form.add(new BasicNameValuePair("choice[1]", "+"));
			form.add(new BasicNameValuePair("choice[2]", "2"));
			form.add(new BasicNameValuePair("choice[3]", "0"));
			form.add(new BasicNameValuePair("save", ""));

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);

			HttpPost httpPost = new HttpPost("https://framadate.org/mSsazZA91jqODqqC");
			httpPost.setHeader("Cookie", "PHPSESSID=5o2lm9ntbrdm26p9jh7sfjjie4");
			httpPost.setEntity(entity);

			System.out.println("Executing request " + httpPost.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity responseEntity = response.getEntity();
					return responseEntity != null ? EntityUtils.toString(responseEntity) : null;
				} else {
					HttpEntity responseEntity = response.getEntity();
					System.out.println(EntityUtils.toString(responseEntity));
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			};
			String responseBody = httpclient.execute(httpPost, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
		}
	}

}