import java.net.*;
import java.io.*;

public class TCPServer
{
    public static void main(String args[]) throws Exception
    {
        // establishing the connection with the server
        ServerSocket welcomesock = new ServerSocket(4000);
        while (true)
        {
            System.out.println("Server ready for connection");
            Socket connectionsock = welcomesock.accept();
            System.out.println("Connection is successful and waiting for client request");

            // reading the file name from client
            InputStream istream = connectionsock.getInputStream();
            BufferedReader fileRead = new BufferedReader(
                    new InputStreamReader(istream));
            String fname = fileRead.readLine();
            System.out.println("User requested " + fname + " file contents");

            try
            {
                BufferedReader contentRead = new BufferedReader(
                        new FileReader(fname));
                System.out.println("File found");

                // keeping output stream ready to send the contents
                OutputStream ostream = connectionsock.getOutputStream();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                String str;

                while ((str = contentRead.readLine()) != null)
                {
                    pwrite.println(str);
                }
                pwrite.close();
                contentRead.close();
                System.out.println("File contents are sent to the client");
            }
            catch (Exception e)
            {
                // e.printStackTrace();
                System.out.println("File not Found");
            }
            fileRead.close();
            connectionsock.close();
        }
        // welcomesock.close();   // closing network sockets
    }
}