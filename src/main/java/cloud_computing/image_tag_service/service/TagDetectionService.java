package cloud_computing.image_tag_service.service;

import cloud_computing.image_tag_service.exception.TagDetectionException;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagDetectionService {
    private static final Integer MAX_RESULTS = 10;
    public List<String> detectTag(InputStream imageInputStream) throws TagDetectionException {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        List<String> tagList = new ArrayList<>();

        try {
            ByteString byteValue = ByteString.readFrom(imageInputStream);

            Image img = Image.newBuilder().setContent(byteValue).build();

            // Feature 타입: 라벨 탐지, 개수: 10
            Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).setMaxResults(MAX_RESULTS).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
            requests.add(request);

            try(ImageAnnotatorClient client = ImageAnnotatorClient.create()){
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();

                for (AnnotateImageResponse res : responses){
                    for(EntityAnnotation annotation : res.getLabelAnnotationsList()){
                        tagList.add(annotation.getDescription());
                    }
                }
            }
        }catch (IOException e) {
            throw new TagDetectionException("태그 감지 오류", e);
        }

        return tagList;
    }
}
