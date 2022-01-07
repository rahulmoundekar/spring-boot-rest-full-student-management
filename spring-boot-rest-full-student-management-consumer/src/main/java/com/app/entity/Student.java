package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Student {

	private Integer id;
	private String name;
	private String mobile;
	private String address;
}