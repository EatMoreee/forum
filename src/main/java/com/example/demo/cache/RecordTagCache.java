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
        food.setTags(Arrays.asList("hot-pot","dry-pot", "cantonese", "xiangcai", "crayfish","barbecue","fried-chicken","dessert","pasta","spicy","northeast-cuisine","western-food","self-service","seafood","kfc"));
        tagDTOS.add(food);

        TagDTO drink = new TagDTO();
        drink.setCategoryName("饮料");
        drink.setTags(Arrays.asList("coco","alittle-tea","HEYTEA","starbucks","LELECHA","happy-lemon"));
        tagDTOS.add(drink);

        TagDTO travel = new TagDTO();
        travel.setCategoryName("游玩");
        travel.setTags(Arrays.asList("celebrity-former-residence","natural-scenery","historical-site","celebrity-punch","resort","park-playground"));
        tagDTOS.add(travel);

        TagDTO play = new TagDTO();
        play.setCategoryName("娱乐");
        play.setTags(Arrays.asList("bar","ktv","gym","video-game-hall","diy","internet-cafe/e-sports","teahouse", "vr"));
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
