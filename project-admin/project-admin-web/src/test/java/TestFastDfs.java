import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.web.utils.FastDFSClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;  
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.After;
import org.junit.Before;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestFastDfs {
    
    public String conf_filename = "D:\\dkz_work_home\\my_workspace\\project-parent\\project-admin\\project-admin-web\\src\\main\\resources\\properties\\fdfs_client.conf"; 
    
    public String local_filename = "D:\\xinxi\\1.png";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    	
    }

    
    @Test
    public void testloud() {
    	String str = "group1/M00/00/00/wKgB2lq4ozOAJvjcAAChEZTQpCY162.jpg";
    	String str1 = str.substring(0, str.indexOf("/"));
    	System.out.println(str1);
    	System.out.println(str.substring(str.indexOf("/")+1 , str.length()));
    }
   

	@SuppressWarnings("restriction")
	@Test
    public void testByteUpload() throws IOException, MyException {
    	ClientGlobal.init(conf_filename);
//    	BASE64Decoder decoder = new BASE64Decoder();
//    	File file = new File("D:\\xinxi\\1.png");
//    	 byte[] buffer = new byte[(int) file.length()];
    	 File file = new File("D:\\xinxi\\2.png");
    	  FileInputStream inputFile = new FileInputStream(file);  
    	  byte[] buffer = new byte[(int) file.length()];  
    	  inputFile.read(buffer);  
    	  inputFile.close();  
    	 
    	 
    	TrackerClient tracker = new TrackerClient(); 
        TrackerServer trackerServer = tracker.getConnection(); 
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer); 
        try {
        	 String fileIds[] = client.upload_file(buffer, "png", null);
        	 System.out.println("组名：" + fileIds[0]); 
             System.out.println("路径: " + fileIds[1]);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @SuppressWarnings("restriction")
	@Test
    public void strbase() throws IOException, MyException {
    	ClientGlobal.init(conf_filename);
    	File file = new File("D:\\xinxi\\2.png");
    	 FileInputStream inputFile = new FileInputStream(file);  
   	  	byte[] buf = new byte[(int) file.length()];  
   	  	inputFile.read(buf);  
   	  	inputFile.close();  
  	    String strbf = new BASE64Encoder().encode(buf);
    	byte[] buffer = new BASE64Decoder().decodeBuffer(strbf);  
    	 
    	TrackerClient tracker = new TrackerClient(); 
        TrackerServer trackerServer = tracker.getConnection(); 
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer); 
        try {
        	 String fileIds[] = client.upload_file(buf, "png", null);
        	 System.out.println("组名：" + fileIds[0]); 
             System.out.println("路径: " + fileIds[1]);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	FileOutputStream out = new FileOutputStream(targetPath);  
//    	out.write(buffer);  
//    	 out.close();  
    };
    
    
    @Test
    public void testUpload() {

        try { 
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer, storageServer); 
            
            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
//          NameValuePair nvp = new NameValuePair("age", "18"); 
            NameValuePair nvp [] = new NameValuePair[]{ 
                    new NameValuePair("age", "18"), 
                    new NameValuePair("sex", "male") 
            }; 
            String fileIds[] = storageClient.upload_file(local_filename, "png", null);
            
            System.out.println(fileIds.length); 
            System.out.println("组名：" + fileIds[0]); 
            System.out.println("路径: " + fileIds[1]);

        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } catch (MyException e) { 
            e.printStackTrace(); 
        } 
    }

    //下载文件，直接以io流的形式下载导本地
    @Test 
    public void testDownload() {
        try {

            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
            byte[] b = storageClient.download_file("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf"); 
            System.out.println(b); 
            IOUtils.write(b, new FileOutputStream("D:/"+UUID.randomUUID().toString()+".conf"));
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
    
    @Test 
    public void testGetFileInfo(){ 
        try { 
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
            FileInfo fi = storageClient.get_file_info("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf"); 
            System.out.println(fi.getSourceIpAddr()); 
            System.out.println(fi.getFileSize()); 
            System.out.println(fi.getCreateTimestamp()); 
            System.out.println(fi.getCrc32()); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
    
    @Test 
    public void testGetFileMate(){ 
        try { 
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, 
                    storageServer); 
            NameValuePair nvps [] = storageClient.get_metadata("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf"); 
            for(NameValuePair nvp : nvps){ 
                System.out.println(nvp.getName() + ":" + nvp.getValue()); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
    
    //http://192.168.59.130/group1/M00/00/00/wKg7glqvGSWAQcGzAAEZUZ7gwnI581.png 12点52分
    @Test 
    public void testDelete(){ 
        try { 
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, 
                    storageServer); 
            int i = storageClient.delete_file("group1", "M00/00/0B/wKgB2lrBz06ATHDPAAEZUZ7gwnI749.jpg"); 
            System.out.println( i==0 ? "删除成功" : "删除失败:"+i); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }
}