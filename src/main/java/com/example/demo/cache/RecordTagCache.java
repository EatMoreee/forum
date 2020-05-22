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
        TagDTO food = new TagDTO();
        food.setCategoryName("美食");
        food.setTags(Arrays.asList("火锅","干锅", "粤菜", "湘菜", "小龙虾","烧烤","炸鸡","甜点","面食","串串","麻辣烫","快餐","东北美食","西餐","自助","海鲜","KFC"));
        tagDTOS.add(food);

        TagDTO drink = new TagDTO();
        drink.setCategoryName("饮料");
        drink.setTags(Arrays.asList("coco","一点点","喜茶","星巴克","乐乐茶","快乐柠檬"));
        tagDTOS.add(drink);

        TagDTO travel = new TagDTO();
        travel.setCategoryName("游玩");
        travel.setTags(Arrays.asList("名人故居","自然风光","历史遗址","网红打卡","度假村","公园游乐场"));
        tagDTOS.add(travel);

        TagDTO play = new TagDTO();
        play.setCategoryName("娱乐");
        play.setTags(Arrays.asList("酒吧","KTV","健身中心","电玩游戏厅","diy手工坊","网吧/电竞","茶馆", "VR"));
        tagDTOS.add(play);


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
