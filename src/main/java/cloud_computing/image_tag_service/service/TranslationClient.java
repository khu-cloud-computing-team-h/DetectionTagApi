package cloud_computing.image_tag_service.service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslationClient {
    private final Translate translate;
    public List<String> translate(List<String> englishTagList){
        return translate.translate(englishTagList, Translate.TranslateOption.targetLanguage("ko")).stream().map(Translation::getTranslatedText).toList();
    }
}
