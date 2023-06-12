package yoon.spring.bbs.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import yoon.spring.bbs.dao.SpDao;
import yoon.spring.bbs.dto.SpDto;

public class SpListCommand implements SpCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String page = request.getParameter("page");
		if (page == null)
			page = "1";
		SpDao dao = new SpDao();
		ArrayList<SpDto> dtos = dao.list(page);
		model.addAttribute("list", dtos);
	}
}
