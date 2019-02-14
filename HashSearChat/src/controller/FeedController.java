package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.FeedCategoryAction;
import action.LikeAction;
import action.feedWriteAction;
import action.RefuseAction;
import action.SearchAction;
import action.feedDeleteAction;
import action.feedMemberAction;
import action.feedModifyAction;
import vo.ActionForward;

@WebServlet("*.fe")
public class FeedController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FeedController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		// 프로젝트명 이후의 /... 주소값을 가져오기 위함.
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/feedWriteAction.fe")) {
			// 경로가 위라면
			action = new feedWriteAction();
			// 페이지를 포워드 하거나 리다이렉트 함.
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/feedForm.fe")) {
			forward = new ActionForward();
			forward.setPath("/feed/feedForm.jsp");
			//setPath의 jsp파일로 이동
		} else if (command.equals("/search.fe")) {
			action = new SearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/likeCount.fe")) {
			action = new LikeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/refuseCount.fe")) {
			action = new RefuseAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/feedDelete.fe")) {
			forward = new ActionForward();
			forward.setPath("/feed/feedDelete.jsp");
		} else if (command.equals("/feedDeleteAction.fe")) {
			action = new feedDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/feedModify.fe")) {
			forward = new ActionForward();
			forward.setPath("/feed/feedModify.jsp");
		} else if (command.equals("/feedModifyAction.fe")) {
			action = new feedModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/feedMemberAction.fe")) {
			action = new feedMemberAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/feedCategoryAction.fe")) {
			action = new FeedCategoryAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("경로오류");
		}
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
