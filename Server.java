package Calculate;
import java.io.*;
import java.net.*;
import java.util.*;
 
public class Server {
    // 소켓과 입력 스트림 초기화
    private Socket socket = null;
 
    // 포트와 함께하는 생성자
    public Server(int port)
    {
        try {
            
            // 클라이언트 요청을 받기 위한 ServerSocket 객체 생성
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();
 
            // dis and dos object for receiving
            // 클라이언트로부터 입력받고 클라이언트에게 출력을 보내기 위한 dis와 dos객체
            DataInputStream dis
                = new DataInputStream(s.getInputStream());
            DataOutputStream dos
                = new DataOutputStream(s.getOutputStream());
 
            while (true) {
                String input = dis.readUTF();
                if (input.equals("bye"))
                    break;
                System.out.println("수식을 받았습니다.");
                int result = 0;
 
                StringTokenizer st
                    = new StringTokenizer(input);
                int oprnd1
                    = Integer.parseInt(st.nextToken());
                String operation = st.nextToken();
                int oprnd2
                    = Integer.parseInt(st.nextToken());
 
                // 서버 객체 생성하고 포트 번호를 5000으로 선정
                if (operation.equals("+")) {
                    result = oprnd1 + oprnd2;
                }
                else if (operation.equals("-")) {
                    result = oprnd1 - oprnd2;
                }
                else if (operation.equals("/")) {
                    result = oprnd1 / oprnd2;
                }
                else if (operation.equals("*")) {
                    result = oprnd1 * oprnd2;
                }
                System.out.println("Sending the Result");
                dos.writeUTF(Integer.toString(result));
            }
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
 
    public static void main(String args[])
    {
        // Server Object and set port number 5000
        Server server = new Server(5000);
    }
}

