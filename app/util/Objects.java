package net.invt.iot.photovoltaic.app.util;

public final class Objects {
	private Objects() {
		throw new RuntimeException("can't instant this opbject");
	}

	public static <T> T noNull(T value, T defaultValue) {
		return value == null ? defaultValue : value;
	}

}
