package PackageK;

import java.io.IOException;
import java.net.ServerSocket;

public class HeartBeatMaster extends ServerSocket{
	
	public HeartBeatMaster() throws IOException{
		super(Configure.HEART_BEAT_PORT);
	}	
}
