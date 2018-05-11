package net.invt.iot.photovoltaic.app.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result<T> {
	private boolean pageable = false;
	private Integer currentPage = 0;
	private Integer total = 0;
	private Integer pageSzie = 0;
	private Integer size = 0;
	private List<?> items = Collections.emptyList();

	private Map<String, Object> extras = new HashMap<>();
	

	public static Result<?> noPageable(List<?> items) {
		return Result.defaults().withItems(items);
	}

	public static Result<?> pageable(Integer total, List<?> items) {
		return Result.defaults().withPageable(true).withTotal(total).withItems(items);
	}

	public static Result<?> defaults() {
		return new Result<>();
	}

	public Result() {
		super();
	}

	public Result(boolean pageable, Integer currentPage, Integer total, Integer pageSzie, Integer size, List<T> items) {
		super();
		this.pageable = pageable;
		this.currentPage = currentPage;
		this.total = total;
		this.pageSzie = pageSzie;
		this.size = size;
		this.items = items;
	}

	public boolean isPageable() {
		return pageable;
	}

	public void setPageable(boolean pageable) {
		this.pageable = pageable;
	}

	public Result<?> withPageable(boolean pageable) {
		this.pageable = pageable;
		return this;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Result<?> withCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		return this;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Result<?> withTotal(Integer total) {
		this.total = total;
		return this;
	}

	public Integer getPageSzie() {
		return pageSzie;
	}

	public void setPageSzie(Integer pageSzie) {
		this.pageSzie = pageSzie;
	}

	public Result<?> withPageSzie(Integer pageSzie) {
		this.pageSzie = pageSzie;
		return this;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Result<?> withSize(Integer size) {
		this.size = size;
		return this;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	public Result<?> withItems(List<?> items) {
		this.items = items;
		return this;
	}

	public Map<String, Object> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, Object> extras) {
		this.extras = extras;
	}
	
	public Result<?> withExtras(Map<String, Object> extras) {
		this.extras = extras;
		return this;
	}
}
