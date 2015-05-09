package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.InterestDto;
import hu.szoftverfolyamat.entity.InterestEntity;
import hu.szoftverfolyamat.repository.InterestRepository;
import hu.szoftverfolyamat.service.mapper.InterestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InterestService {

    @Autowired
    InterestMapper interestMapper;

    @Autowired
    InterestRepository interestRepository;

    public void create(final String name) {
        final InterestEntity entity = new InterestEntity();
        entity.setName(name);
        entity.setCreatedAt(new Date());
        interestRepository.saveAndFlush(entity);
    }

    public List<InterestDto> getAll() {
        return interestMapper.apply(interestRepository.findAll());
    }

    public List<InterestDto> getMostUsedOnes() {
        return interestMapper.apply(interestRepository.findMostUsed());
    }
}
