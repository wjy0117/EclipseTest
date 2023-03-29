import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB_Conn {

	public String _Sql;

	Connection conn = null; // db접속 객체
	Statement stmt = null;
	ResultSet res = null;
	public DB_Conn() { //생성자1
		Connection();
	}

	public DB_Conn(String _Sql) { //생성자2
		Connection();
		this._Sql = _Sql;
	}

	void Connection() {

		try {
			// mysql jdbc driver 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// db연결 문자열 but 이방법은 보안에 취약하다.
			String url = "jdbc:mysql://localhost/mmn?characterEncoding=UTF-8&serverTimezone=UTC";
			String id = "root"; // mysql 접속아이디
			String pwd = "1234"; // mysql 접속 비번

			// db 접속
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("db접속 성공");
		} catch (Exception e) {
			// db관련작업은 반드시 익셉션 처리
			System.out.println("db접속 실패");
			e.printStackTrace();
		}

	}
	
	public void Login_UserData(Insert_LoginData _Data) {
		//Connection conn = null; // DB접속 객체
		PreparedStatement pstmt = null; // SQL실행객체
		try {
			String sql = "SELECT USER_ID, USER_PW FROM User_info";
			//pstmt = conn.preparestatement(sql);
			//pstmt.executeupdate();
			
			//데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체생성
			stmt = conn.createStatement();
			//Select 문에서만 실행하며 데이터베이스에서 데이터를 가져와서 결과 집합을 반환
			res = stmt.executeQuery(sql);
			
			if(res.next())
			{
				String _id = res.getString("USER_ID");
				String _pw = res.getString("USER_PW");
				
				if(_id.equals(_Data.ID))
				{
					System.out.println("로그인완료_아이디 같음");
				}
			}
			/*
			 * String sql = "insert into User_info(USER_ID, USER_PW, USER_GENDER, USER_BIRTH, USER_PNUM)" + " values(?, ?, ?, ?, ?)";

			// sql 실행객체 생성
			pstmt = conn.prepareStatement(sql);

			// ? 에 입력될 값 매핑
			pstmt.setString(1, _Data.ID);
			pstmt.setString(2, _Data.PW);
			pstmt.setString(3, _Data.Gender);
			pstmt.setString(4, _Data.Birth);
			pstmt.setString(5, _Data.Phone_Num);

			// executeQuery() select 명령어
			// executeUpdate select 이외 명령어
			pstmt.executeUpdate();
			
			System.out.println("작업완료");
			*/
			System.out.println("작업완료");
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			// 리소스 정리작업
			try {
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

}
