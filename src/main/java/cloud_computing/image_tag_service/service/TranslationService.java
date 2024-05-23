package cloud_computing.image_tag_service.service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslationService {

    public List<String> translate(List<String> englishTagList){
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        return translate.translate(englishTagList, Translate.TranslateOption.targetLanguage("ko")).stream().map(Translation::getTranslatedText).toList();
    }
}
