package yoon.spring.bbs.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import yoon.spring.bbs.dao.SpDao;

public class SpDeleteCommand implements SpCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String cNum = request.getParameter("num");

		SpDao dao = new SpDao();
		dao.delete(cNum);

	}

}
