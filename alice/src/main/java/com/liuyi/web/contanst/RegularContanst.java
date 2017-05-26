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
    
    public final static String IP = "121.226.166.56";
    
    public final static String PORT = "808";
    
    public final static String[] userAgent= {"Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5","Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5","Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5","Mozilla/5.0 (Linux; U; Android 2.3.7; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1","MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1","Mozilla/5.0 (BlackBerry; U; BlackBerry 9800; en) AppleWebKit/534.1+ (KHTML, like Gecko) Version/6.0.0.337 Mobile Safari/534.1+","Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; HTC; Titan)"};

}
