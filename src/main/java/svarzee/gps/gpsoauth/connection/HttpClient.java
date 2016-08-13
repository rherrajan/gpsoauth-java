package svarzee.gps.gpsoauth.connection;

import okhttp3.Call;
import okhttp3.Request;

public interface HttpClient {

	Call newCall(Request request);

}
