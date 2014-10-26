package PackageK;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Runner {
	private Vector<SiteRunner>	vecSite = new Vector<SiteRunner>();
	private AccessDB	dbPoint;
	private HeartBeatMaster heartBeatMaster;	
	
	public Runner(){
		dbPoint = new AccessDB();
		dbPoint.connect();
		try {
			heartBeatMaster = new HeartBeatMaster();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
	}
	
	private void init(){
		// �ν��Ͻ� 3�� ����.
		vecSite.add(new SiteRunner("Naver", heartBeatMaster));
		vecSite.add(new SiteRunner("Daum", heartBeatMaster));
		vecSite.add(new SiteRunner("Nate", heartBeatMaster));
	}
	
	public void run(){
		while (true){
			// üũ�ؼ� ������ ����.
			for (SiteRunner	siteRunner : vecSite){
				if (!siteRunner.isAlive()){
					siteRunner.run();
				}
			}
			// ����, ī��Ʈ �����.
			String status = "";
			for (SiteRunner	siteRunner : vecSite){
				status += siteRunner.toString() + "\r\n";
			}
			status += "DB Content Count : " + dbPoint.getRowCount();
			
			System.out.println();
			System.out.println("====================");
			System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date()));
			System.out.println(status);
			// 1�� ��.
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
