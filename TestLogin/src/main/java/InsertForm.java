
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertForm
 */
@WebServlet("/Insert")
public class InsertForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			DB_Conn _Db = new DB_Conn();
			
			// getParameter 메서드로 input태그의 name속성 값으로 전송된 값을 받아옴
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");

			Insert_LoginData _Data = new Insert_LoginData();
			_Data.ID = user_id;
			_Data.PW = user_pw;

			_Db.Login_UserData(_Data);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
