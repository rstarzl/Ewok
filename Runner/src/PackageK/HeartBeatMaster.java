package PackageK;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HeartBeatMaster extends ServerSocket{
	
	public HeartBeatMaster() throws IOException{
		super(Configure.HEART_BEAT_PORT);
	}
	
//	@Override
//	public Socket accept(){
//		Socket	ret = super.accept();
//				
//		return null;
//	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//	}
}
