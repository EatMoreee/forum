package com.example.demo.service;

import com.example.demo.Model.*;
import com.example.demo.dto.JqueryDTO;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.ShareDTO;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.ShareExMapper;
import com.example.demo.mapper.ShareMapper;
import com.example.demo.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShareService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private ShareExMapper shareExMapper;

    public PaginationDTO list(String search, Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }

        JqueryDTO jqueryDTO = new JqueryDTO();
        jqueryDTO.setSearch(search);
        Integer totalCount = (int) shareExMapper.countBySearch(jqueryDTO);
        Integer PageNum;
        if(totalCount % size == 0) PageNum = totalCount / size;
        else PageNum = totalCount / size + 1;
        if(page > PageNum) page = PageNum;
        if(page < 1) page = 1;

        Integer offset = (page - 1) * size;
        jqueryDTO.setPage(offset);
        jqueryDTO.setSize(size);
        List<Share> shares = shareExMapper.selectBySearch(jqueryDTO);
        List<ShareDTO> shareDTOS = new ArrayList<>();

        PaginationDTO<ShareDTO> paginationDTO = new PaginationDTO<>();
        for (Share share: shares) {
            User user = userMapper.selectByPrimaryKey(share.getCreator());
            ShareDTO shareDTO = new ShareDTO();
            BeanUtils.copyProperties(share, shareDTO);
            shareDTO.setUser(user);
            shareDTOS.add(shareDTO);
        }
        paginationDTO.setData(shareDTOS);
        paginationDTO.setPagination(PageNum, page, size);
        return paginationDTO;
    }

    public void createOrUpdate(Share share) {
        if (share.getId() == null) {
            share.setGmtCreate(System.currentTimeMillis());
            share.setViewCount(0L);
            share.setLikeCount(0L);
            share.setCommentCount(0L);
            share.setDownloadCount(0L);
            shareMapper.insert(share);
        }
        else {
            Share updateShare = new Share();
            updateShare.setGmtCreate(System.currentTimeMillis());
            updateShare.setTitle(share.getTitle());
            updateShare.setDescription(share.getDescription());
            updateShare.setTag(share.getTag());
            ShareExample shareExample = new ShareExample();
            shareExample.createCriteria()
                    .andIdEqualTo(share.getId());
            int update = shareMapper.updateByExampleSelective(updateShare, shareExample);
            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.SHARE_NOT_FOUND);
            }
        }
    }

    public ShareDTO getById(Long id) {
        Share share = shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw  new CustomizeException(CustomizeErrorCode.SHARE_NOT_FOUND);
        }
        ShareDTO shareDTO = new ShareDTO();
        User user = userMapper.selectByPrimaryKey(share.getCreator());
        shareDTO.setUser(user);
        BeanUtils.copyProperties(share, shareDTO);
        return shareDTO;
    }

    public List<ShareDTO> selectRelate(ShareDTO shareDTO) {
        if (StringUtils.isBlank(shareDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(shareDTO.getTag(),",|，");
        String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Share share = new Share();
        share.setId(shareDTO.getId());
        share.setTag(regexTag);

        List<Share> shares = shareExMapper.selectRelate(share);
        List<ShareDTO> shareDTOS = shares.stream().map(q -> {
            ShareDTO shareDTO1 = new ShareDTO();
            BeanUtils.copyProperties(q, shareDTO1);
            return shareDTO1;
        }).collect(Collectors.toList());
        return shareDTOS;
    }

    public void incView(Long id) {
        Share share = new Share();
        share.setId(id);
        share.setViewCount(1L);
        shareExMapper.incView(share);
    }

    public List<Share> HotList() {
        ShareExample shareExample = new ShareExample();
        shareExample.setOrderByClause("view_count desc");
        List<Share> shares = shareMapper.selectByExample(shareExample);
        List<Share> shareList = new ArrayList<>();
        for(int i = 0; i < shares.size() && i < 10; ++i) {
            shareList.add(shares.get(i));
        }
        return shareList;
    }

    public List<Share> searchKey(String search) {
        ShareExample shareExample = new ShareExample();
        shareExample.setOrderByClause("view_count desc");
        List<Share> shares = shareMapper.selectByExample(shareExample);
        List<Share> shareList = new ArrayList<>();
        for(Share share : shares) {
            if(share.getTitle().indexOf(search) != -1) {
                shareList.add(share);
            }
            else if (share.getDescription().indexOf(search) != -1) {
                shareList.add(share);
            }
            else if (share.getTag().indexOf(search) != -1) {
                shareList.add(share);
            }
        }
        return shareList;
    }

    public void incLike(Long id) {
        Share share = new Share();
        share.setId(id);
        share.setLikeCount(1L);
        shareExMapper.incLike(share);
    }
}
