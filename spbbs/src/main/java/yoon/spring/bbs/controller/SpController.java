package yoon.spring.bbs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import yoon.spring.bbs.command.SpCommand;
import yoon.spring.bbs.command.SpDeleteCommand;
import yoon.spring.bbs.command.SpDetailCommand;
import yoon.spring.bbs.command.SpListCommand;
import yoon.spring.bbs.command.SpReplyCommand;
import yoon.spring.bbs.command.SpReplyokCommand;
import yoon.spring.bbs.command.SpUpdateCommand;
import yoon.spring.bbs.command.SpUpdateokCommand;
import yoon.spring.bbs.command.SpWriteCommand;
import yoon.spring.bbs.dto.PageDto;
import yoon.spring.bbs.util.Pagination;
import yoon.spring.bbs.util.Static;

@Controller
public class SpController {

	// 모든 command 가 갖고 있는 인터페이스 타입을 선언
	SpCommand command;

	// jdbc Spring template
	public JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Static.template = template;
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("list()실행");
		model.addAttribute("request", request);
		command = new SpListCommand();
		command.execute(model);

		PageDto pdto = new PageDto();
		Pagination pages = new Pagination();
		pages.setPdto(pdto);
		pages.setDisplayPageNum(10);
		pages.setTotalCount();
		model.addAttribute("pages", pages);

		return "list";
	}

	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, Model model) {
		System.out.println("detail()");

		model.addAttribute("request", request);
		command = new SpDetailCommand();
		command.execute(model);

		return "detail";
	}

	@RequestMapping("/write")
	public String write(Model model) {
		System.out.println("write()");
		return "write";
	}

	@RequestMapping(value = "/writeok", method = RequestMethod.POST)
	public String writeok(HttpServletRequest request, Model model) {
		System.out.println("writeok");

		model.addAttribute("request", request);
		command = new SpWriteCommand();
		command.execute(model);

		return "redirect:list";
	}

	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");

		model.addAttribute("request", request);
		command = new SpReplyCommand();
		command.execute(model);

		return "reply";
	}

	@RequestMapping(value = "/replyok", method = RequestMethod.POST)
	public String replyok(HttpServletRequest request, Model model) {
		System.out.println("replyok()");

		model.addAttribute("request", request);
		command = new SpReplyokCommand();
		command.execute(model);

		return "redirect:list";
	}

	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model) {
		System.out.println("update()");

		model.addAttribute("request", request);
		command = new SpUpdateCommand();
		command.execute(model);

		return "update";
	}

	@RequestMapping(value = "/updateok", method = RequestMethod.POST)
	public String updateok(HttpServletRequest request, Model model) {
		System.out.println("updateok()");

		model.addAttribute("request", request);
		command = new SpUpdateokCommand();
		command.execute(model);

		return "redirect:list";
	}

	@RequestMapping(value = "/del")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");

		model.addAttribute("request", request);
		command = new SpDeleteCommand();
		command.execute(model);

		return "redirect:list";
	}
}
