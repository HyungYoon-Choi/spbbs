package yoon.spring.bbs.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import yoon.spring.bbs.dao.SpDao;
import yoon.spring.bbs.dto.SpDto;

public class SpUpdateCommand implements SpCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String num = request.getParameter("num");

		SpDao dao = new SpDao();
		SpDto dto = dao.update(num);

		model.addAttribute("update", dto);

	}
}
