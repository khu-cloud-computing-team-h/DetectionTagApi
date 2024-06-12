package cloud_computing.image_tag_service.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagRepository {
    private final StringRedisTemplate redisTemplate;
    public void save(BigDecimal userId, List<String> tagList){
        // 기존 값 삭제
        redisTemplate.delete(userId.toString());
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        // 태그 추가
        listOps.rightPushAll(userId.toString(), tagList);
    }

    public List<String> findALL(BigDecimal userId){
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.range(userId.toString(), 0, -1);
    }

}
