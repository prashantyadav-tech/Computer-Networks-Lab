import java.net.*;
import java.io.*;

public class TCPClient
{
    public static void main(String args[]) throws Exception
    {
        Socket clientsock = new Socket("127.0.0.1", 4000);

        // reading the file name from keyboard. Uses input streams
        System.out.println("Enter the file name");
        BufferedReader keyRead = new BufferedReader(
                new InputStreamReader(System.in));
        String fname = keyRead.readLine();

        // sending the file name to server
        OutputStream ostream = clientsock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        pwrite.println(fname);

        // receiving the contents from server. Uses input stream
        InputStream istream = clientsock.getInputStream();
        BufferedReader socketRead = new BufferedReader(
                new InputStreamReader(istream));

        String str;
        if ((str = socketRead.readLine()) == null)
        {
            System.out.println("File not Found");
        }
        else
        {
            System.out.println("File contents");
            do
            {
                // reading line-by-line
                System.out.println(str);
            }
            while ((str = socketRead.readLine()) != null);
        }

        pwrite.close();
        socketRead.close();
        keyRead.close();
        clientsock.close();
    }
}