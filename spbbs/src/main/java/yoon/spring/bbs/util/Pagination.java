package yoon.spring.bbs.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import yoon.spring.bbs.dao.SpDao;
import yoon.spring.bbs.dto.PageDto;

public class Pagination {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum;
	private PageDto pdto;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount() {
		SpDao dao = new SpDao();
		this.totalCount = dao.totalRecord();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		startPage = (getEndPage() - getDisplayPageNum()) + 1;
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		endPage = (int) (Math.ceil(pdto.getPage() / (double) getDisplayPageNum()) * getDisplayPageNum());
		int tempPage = (int) (Math.ceil(getTotalCount() / (double) pdto.getPerPageNum()));
		if (endPage > tempPage) {
			this.endPage = tempPage;
		} else {
			this.endPage = endPage;
		}
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = getStartPage() == 1 ? false : true;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = getEndPage() * pdto.getPerPageNum() >= getTotalCount() ? false : true;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Pagination() {
	}

	public String makeQuery(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page).build();

		return uriComponents.toString();

	}

	public PageDto getPdto() {
		return pdto;
	}

	public void setPdto(PageDto pdto) {
		this.pdto = pdto;
	}

}
