package svarzee.gps.gpsoauth;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientWrapper implements HttpClient{

	private OkHttpClient httpClient;

	public OkHttpClientWrapper(OkHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public Response execute(Request request) throws IOException {
	    return httpClient.newCall(request).execute();
	}

}
