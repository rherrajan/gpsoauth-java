package svarzee.gps.gpsoauth.connection;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public interface HttpClient {

	Response execute(Request request) throws IOException;

}
