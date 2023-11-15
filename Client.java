package Calculate;
import java.io.*;
import java.net.*;
import java.util.*;
 
public class Client {
    private Socket s = null;
    public Client(String address, int port)
    {
        try {
            // 사용자로부터 입력받기
            Scanner sc = new Scanner(System.in);
            s = new Socket(address, port);
            System.out.println("서버에 연결되었습니다.");
 
            // 입출력을 위한 두 개의 객체 생성
            DataInputStream dis
                = new DataInputStream(s.getInputStream());
            DataOutputStream dos
                = new DataOutputStream(s.getOutputStream());
 
            // 루프 시작
            while (true) {
                System.out.println(
                    "예를 들어 3 + 5,3 * 5 형식으로 연산을 입력하세요.");

                String inp = sc.nextLine();
 
                // 사용자 입력이 “Over”인 경우 서버와의 연결 종료
                if (inp.equals("Over"))
                    break;
                dos.writeUTF(inp);
 
                String ans = dis.readUTF();
                System.out.println("답변 =" + ans);
            }
        }
        catch (Exception e) {
            System.out.println("연결 오류 발생");
        }
    }
 
    public static void main(String args[])
    {
        // 서버 포트 5000에 연결
        Client client = new Client("127.0.0.1", 5000);
    }
}

