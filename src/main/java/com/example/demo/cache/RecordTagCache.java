package com.example.demo.cache;

import com.example.demo.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecordTagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("Javascript","php", "css", "html", "html5","java","node.js","python","c++","c","golang","objective-c","typescript","shell","swift","c#","sass","ruby","bash","less","asp.net","perl"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel","spring","express","django","flask","ruby-on-rails","tornado","koa", "struts","yii"));
        tagDTOS.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","docker","apache","ubuntu","centos","tomcat","unix","hadoop", "windows-server"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","mongodb","sql","oracle","sql server","nosql memcached", "postqresql", "sqlite"));
        tagDTOS.add(db);

        TagDTO tools = new TagDTO();
        tools.setCategoryName("开发工具");
        tools.setTags(Arrays.asList("git","github","visual-studio-code", "vim", "sublime", "intellij-idea", "eclipse", "maven", "ide", "svn", "visual-stdio", "atom emacs", "textmate", "hg"));
        tagDTOS.add(tools);

        return tagDTOS;
    }

    public static String filterIsValid(String tags) {
        String[] split = StringUtils.split(tags, ",|，");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String isValid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(",|，"));
        return isValid;
    }
}
