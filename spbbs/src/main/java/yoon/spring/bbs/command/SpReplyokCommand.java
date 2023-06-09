package yoon.spring.bbs.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import yoon.spring.bbs.dao.SpDao;

public class SpReplyokCommand implements SpCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int s_group = Integer.parseInt(request.getParameter("s_group"));
		int s_step = Integer.parseInt(request.getParameter("s_step")) + 1;
		int s_indent = Integer.parseInt(request.getParameter("s_indent")) + 1;

		SpDao dao = new SpDao();
		dao.replyok(s_group, s_step, s_indent, uname, upass, title, content);

	}

}
