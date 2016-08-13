package svarzee.gps.gpsoauth.connection;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientWrapper implements HttpClient{

	protected OkHttpClient httpClient;

	public OkHttpClientWrapper(OkHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public Response execute(Request request) throws IOException {
	    Call newCall = httpClient.newCall(request);
	    System.out.println(" --- newCall: " + newCall);
		return newCall.execute();
	}

}
