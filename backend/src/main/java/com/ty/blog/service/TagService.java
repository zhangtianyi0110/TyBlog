package com.ty.blog.service;

import com.ty.blog.base.BaseService;
import com.ty.blog.entity.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *  @ClassName: TagService
 *  @Description: TODO
 *  @author zhangtianyi
 *  @Date 2019/12/13 10:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TagService extends BaseService {

    /**
     * 保存标签
     * @param tagNames
     * @return
     */
    public Set<Tag> saveTags(Set<String> tagNames){
        Set<Tag> newTags = new HashSet<>();
        Set<Tag> tags = tagDao.findAllByTagNameIn(tagNames);
        tags.forEach(tag -> {
            if(tagNames.contains(tag.getTagName())){
                tagNames.remove(tag.getTagName());
            }
        });
        tagNames.forEach(tagName -> {
            newTags.add(Tag.builder()
                    .tagName(tagName)
                    .createTime(Calendar.getInstance().getTime())
                    .modifyTime(Calendar.getInstance().getTime()).build());
        });
        tagDao.saveAll(newTags);
        tagDao.flush();
        newTags.addAll(tags);
        return tagDao.findAllByTagNameIn(newTags
                .stream()
                .map(newTag -> newTag.getTagName())
                .collect(Collectors.toSet()));
    }

}
