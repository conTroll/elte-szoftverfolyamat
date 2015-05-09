package hu.szoftverfolyamat.service.mapper;

import hu.szoftverfolyamat.dto.InterestDto;
import hu.szoftverfolyamat.entity.InterestEntity;
import org.springframework.stereotype.Service;

@Service
public class InterestMapper extends AbstractMapper<InterestEntity, InterestDto> {

    @Override
    public InterestDto apply(final InterestEntity entity) {
        final InterestDto result = new InterestDto();
        result.setCreatedAt(entity.getCreatedAt());
        result.setId(entity.getId());
        result.setName(entity.getName());
        return result;
    }
}
