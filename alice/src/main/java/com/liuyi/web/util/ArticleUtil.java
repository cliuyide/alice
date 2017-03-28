package com.liuyi.web.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.liuyi.web.model.HtmlMessage;

public class ArticleUtil {
    public HtmlMessage createArticleMessage(String localPath)
            throws IOException, ParseException {
        HtmlMessage hm = new HtmlMessage();
        try {
            File in = new File(localPath);
//          Document dom = org.jsoup.Jsoup.parse(in, "GB2312");
          Document dom = org.jsoup.Jsoup.connect(localPath).get();
          /// 关键字
          Elements keywordsEl = dom.getElementsByAttributeValue("name", "keywords");
          String keywords = keywordsEl.get(0).attr("content");
          /// 责任编辑
          Elements editorEl = dom.getElementsByAttributeValue("class", "ep-editor");
          String editor = editorEl.get(0).text();
          /// 正文元素获取
          Element mainBody = dom.getElementById("epContentLeft");
          /// 标题
          String head = mainBody.getElementsByTag("h1").text();
          /// 事件
          String postTime = mainBody
                  .getElementsByAttributeValue("class", "post_time_source").get(0).text();
//         String time = "\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}\\s{1}\\d{2}:\\d{2}:\\d{2}";
          Pattern r = Pattern.compile("\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}\\s{1}\\d{2}:\\d{2}:\\d{2}");
          Matcher m = r.matcher(postTime.trim());
          if (m.find()) {
              postTime = m.group(0);
          }
          String titleSource = mainBody.getElementById("ne_article_source").text();
          String content = mainBody.getElementById("endText").text();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 小写的mm表示的是分钟
          Date date = sdf.parse(postTime);        
          hm.setId(UUID.randomUUID().toString());
          hm.setArticleCreateTime(date);
          hm.setKeywords(keywords);
          hm.setCreateTime(new Date());
          hm.setEditor(editor);
          hm.setUrl(localPath);
          hm.setTitle(head);
          hm.setArticleSource(titleSource);
          hm.setArticleContent(content);
          System.out.println("关键字:" + keywords + "责任编辑" + editor + "标题:" + head + "时间:"
                  + postTime + "来源:" + titleSource + "正文:" + content);
        } catch (Exception e) {
           
        }
       
        return hm;
    }
}
