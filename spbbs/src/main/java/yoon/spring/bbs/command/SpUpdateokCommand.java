package yoon.spring.bbs.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import yoon.spring.bbs.dao.SpDao;

public class SpUpdateokCommand implements SpCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String num = request.getParameter("num");
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		SpDao dao = new SpDao();
		dao.updateok(num, uname, upass, title, content);
	}

}
