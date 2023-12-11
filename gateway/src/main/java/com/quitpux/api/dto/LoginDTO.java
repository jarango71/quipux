package com.quitpux.api.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder
public class LoginDTO implements Serializable {
	private static final long serialVersionUID = -6587730290416571673L;
	private String username;
	private String password;
}
