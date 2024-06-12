package cloud_computing.image_tag_service.service;

import cloud_computing.image_tag_service.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagDetectionClient tagDetectionClient;
    private final TranslationClient translationClient;
    private final TagRepository tagRepository;

    public void createTags(BigDecimal userId, InputStream inputStream){
        List<String> englishTagList = tagDetectionClient.detectTag(inputStream);
        List<String> koreanTagList = translationClient.translate(englishTagList);
        tagRepository.save(userId, koreanTagList);
    }

    public List<String> findTags(BigDecimal userId){
        return tagRepository.findALL(userId);
    }

}
