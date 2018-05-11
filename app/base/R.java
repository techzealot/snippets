package net.invt.iot.photovoltaic.app.base;

import java.util.HashMap;

import net.invt.iot.photovoltaic.app.util.Objects;

public class R extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_STATUS = 0;

	private static final String DEFAULT_MESSAGE = "";
	private static final String DEFAULT_SUCCESS_MESSAGE = "ok";
	private static final String DEFAULT_ERROR_MESSAGE = "error";
	private static final String DEFAULT_DENY_MESSAGE = "access denied";

	private static final Result<?> DEFAULT_DATA = new Result<>();

	public R() {
		this(null, null, null);
	}

	public R(Integer status) {
		this(status, null, null);
	}

	public R(Integer status, String message) {
		this(status, message, null);
	}

	public R(Integer status, String message, Result<?> data) {
		super();
		this.put("status", Objects.noNull(status, DEFAULT_STATUS));
		this.put("message", Objects.noNull(message, DEFAULT_MESSAGE));
		this.put("data", Objects.noNull(data, DEFAULT_DATA));
	}

	public R status(Integer status) {
		this.put("status", Objects.noNull(status, DEFAULT_STATUS));
		return this;
	}

	public R message(String message) {
		this.put("message", Objects.noNull(message, DEFAULT_MESSAGE));
		return this;
	}

	public R data(Result<?> data) {
		this.put("data", Objects.noNull(data, DEFAULT_DATA));
		return this;
	}

	public static R ok(String message, Result<?> data) {
		return new R(0, Objects.noNull(message, DEFAULT_SUCCESS_MESSAGE), data);
	}

	public static R error(Integer status, String message) {
		return new R(status, Objects.noNull(message, DEFAULT_ERROR_MESSAGE), null);
	}

	public static R error(Integer status, String message, Result<?> data) {
		return new R(status, Objects.noNull(message, DEFAULT_ERROR_MESSAGE), data);
	}

	public static R noPermission() {
		return new R(401, DEFAULT_DENY_MESSAGE, null);
	}

	public static R noPermission(String message, Result<?> data) {
		return new R(401, Objects.noNull(message, DEFAULT_DENY_MESSAGE), data);
	}

	public static R of(Integer status, String message, Result<?> data) {
		return new R(status, Objects.noNull(message, DEFAULT_SUCCESS_MESSAGE), data);
	}

	public static R defaults() {
		return new R();
	}

	public Result<?> getData() {
		return (Result<?>) this.get("data");
	}

	public String getMessage() {
		return (String) this.get("message");
	}

	public Integer getStatus() {
		return (Integer) this.get("status");
	}
}
