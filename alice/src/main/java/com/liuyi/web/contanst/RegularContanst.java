package com.liuyi.web.contanst;

public class RegularContanst {
	/**
	 * 网易的正则
	 */
	public final static String WANG_YI = "[a-zA-z]+://[^\\s]+/[^\\D+]+/[^\\D+]+/[^\\D+]+/.*";
	/**
     * 美团美食
     */
	public final static String MEITUAN_DEAL = "[a-zA-z]+://[^\\s]+?/deal/\\d+.html";
	/**
     * 美食父菜单优先抽取
     */
	public final static String CATEGORY_DEAL = "[a-zA-z]+://[^\\s]+?/category/+.*";
}
