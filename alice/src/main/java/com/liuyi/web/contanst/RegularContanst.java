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
	/**
     * 美食商店菜单
     */
    public final static String SHOP_DEAL = "[a-zA-z]+://[^\\s]+?/shop/+.*";
    
    public final static String IP = "175.155.24.13";
    
    public final static String PORT = "808";
	
}
