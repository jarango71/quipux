package com.quitpux.api.dto;


import java.io.Serializable;
import java.net.URI;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class RouteDTO implements Serializable {
	private static final long serialVersionUID = 7885373191295584329L;
	private String name;
	private String path;
	private URI uri;
}
