package svarzee.gps.gpsoauth.connection;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientReplacement implements HttpClient{

	@Override
	public Call newCall(Request request) {
		return new SandboxCall(request);
	}

}
