package yoon.spring.bbs.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import yoon.spring.bbs.dao.SpDao;
import yoon.spring.bbs.dto.SpDto;

public class SpListCommand implements SpCommand {

	@Override
	public void execute(Model model) {
		SpDao dao = new SpDao();
		ArrayList<SpDto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}
}
