package svarzee.gps.gpsoauth.connection;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpClientWrapper implements HttpClient{

	protected OkHttpClient httpClient;

	public OkHttpClientWrapper(OkHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public Call newCall(Request request) {
		return httpClient.newCall(request);
	}

}
