package net.invt.iot.photovoltaic.app.base;

import javax.servlet.http.HttpServletRequest;

/**
 * 对于支持分页的返回数据必须调用该方法完善分页信息
 * 
 * @author Administrator
 *
 */
public class RWrapper {
	public static R page(R r, HttpServletRequest req) {
		Result<?> result = r.getData();
		// 将分页标志置为true
		result.setPageable(true);
		int size = result.getItems().size();
		// 设置实际返回的条数
		result.setSize(size);
		Object currentPage = req.getParameter("currentPage");
		if (currentPage != null) {
			// 设置当前页
			result.setCurrentPage(Integer.valueOf(currentPage.toString()));
		}
		Object pageSize = req.getParameter("pageSize");
		if (currentPage != null) {
			// 设置页数
			result.setCurrentPage(Integer.valueOf(pageSize.toString()));
		}
		// 可以设置额外数据,支持扩展
		//result.setExtras(extras);
		return r;
	}

}
