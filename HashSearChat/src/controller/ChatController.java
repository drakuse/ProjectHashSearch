package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ChatCategoryAction;
import action.ChatDeleteAction;
import action.ChatInfoAction;
import action.ChatListAction;
import action.ChatMemberAction;
import action.ChatModifyAction;
import action.ChatWriteAction;
import vo.ActionForward;

@WebServlet("*.ch")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChatController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequesetURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequesetURI.substring(contextPath.length());
		// 프로젝트명 이후의 /... 주소값을 가져오기 위함.
		ActionForward forward = null;
		
		Action action = null;
		
		if (command.equals("/chatWriteAction.ch")) {
			// 경로가 위라면
			action = new ChatWriteAction();
			// 페이지를 포워드 하거나 리다이렉트 함.
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/chatWriteForm.ch")) {
			forward = new ActionForward();
			forward.setPath("/chat/chatWrite.jsp");
		} else if (command.equals("/chatListAction.ch")) {
			action = new ChatListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/chatInfoAction.ch")) {
			action = new ChatInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/chatDelete.ch")) {
			forward = new ActionForward();
			forward.setPath("/chat/chatDelete.jsp");
		} else if (command.equals("/chatDeleteAction.ch")) {
			action = new ChatDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/chatModify.ch")) {
			forward = new ActionForward();
			forward.setPath("/chat/chatModify.jsp");
		} else if (command.equals("/chatModifyAction.ch")) {
			action = new ChatModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/chatMemberAction.ch")) {
			action = new ChatMemberAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/chatCategoryAction.ch")) {
			action = new ChatCategoryAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
