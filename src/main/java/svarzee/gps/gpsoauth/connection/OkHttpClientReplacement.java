package svarzee.gps.gpsoauth.connection;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientReplacement implements HttpClient{

	@Override
	public Response execute(Request request) throws IOException {
		
        return new SandboxCall(request).execute();
    }

}
