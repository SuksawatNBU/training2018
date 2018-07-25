package com.cct.trn.core.config.parameter.domain;

import java.io.Serializable;
import java.util.Map;

public class ParameterConfig implements Serializable {

	private static final long serialVersionUID = -2187894195556282622L;

	private static Parameter parameter;
	private static Map<String, String> mapContenType;

	public static Parameter getParameter() {
		return parameter;
	}

	public static void setParameter(Parameter parameter) {
		ParameterConfig.parameter = parameter;
	}

	public static Map<String, String> getMapContenType() {
		return mapContenType;
	}

	public static void setMapContenType(Map<String, String> mapContenType) {
		ParameterConfig.mapContenType = mapContenType;
	}

}
