/**
 * 
 */
package com.booksys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Liuyang
 * @date 2017年7月4日 下午9:35:34
 */
@Controller
@RequestMapping("/map")
public class MapController {
	@RequestMapping("/toMap")
	public String toMap(){
		return "map";
	}
}
