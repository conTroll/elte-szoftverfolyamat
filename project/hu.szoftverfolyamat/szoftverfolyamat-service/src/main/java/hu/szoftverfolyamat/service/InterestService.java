package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.ChannelProfileDto;
import hu.szoftverfolyamat.dto.InterestDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.ChannelProfileEntity;
import hu.szoftverfolyamat.entity.InterestEntity;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.repository.ChannelRepository;
import hu.szoftverfolyamat.repository.InterestRepository;
import hu.szoftverfolyamat.repository.UserProfileDataRepository;
import hu.szoftverfolyamat.service.mapper.ChannelProfileMapper;
import hu.szoftverfolyamat.service.mapper.InterestMapper;
import hu.szoftverfolyamat.service.mapper.UserProfileDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InterestService {

    @Autowired
    InterestMapper interestMapper;

    @Autowired
    UserProfileDataMapper userProfileDataMapper;

    @Autowired
    ChannelProfileMapper channelProfileMapper;

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    UserProfileDataRepository userProfileDataRepository;

    @Autowired
    ChannelRepository channelRepository;

    public InterestDto getById(long id) {
        final InterestEntity entity = interestRepository.findOne(id);

        if (entity == null) {
            return null;
        }

        return interestMapper.apply(entity);
    }

    public List<UserProfileDataDto> getUsersForInterest(long id) {
        final InterestEntity entity = interestRepository.findOne(id);

        if (entity == null) {
            return new ArrayList<>();
        }

        return userProfileDataMapper.apply(entity.getUsers());
    }

    public List<ChannelProfileDto> getChannelsForInterest(long id) {
        final InterestEntity entity = interestRepository.findOne(id);

        if (entity == null) {
            return new ArrayList<>();
        }

        return channelProfileMapper.apply(entity.getChannels());
    }

    public List<InterestDto> getAll() {
        return interestMapper.apply(interestRepository.findAll());
    }

    public List<InterestDto> getWithPrefix(final String prefix) {
        final List<InterestDto> interests = interestMapper.apply(interestRepository.findAll());
        final List<InterestDto> result = new ArrayList<>();

        for (final InterestDto interest : interests) {
            if (interest.getName().startsWith(prefix)) {
                result.add(interest);
            }
        }

        return result;
    }

    public List<InterestDto> getUserInterests(final long userId) {
        final UserProfileData user = userProfileDataRepository.findOne(userId);

        if (user == null) {
            return new ArrayList<>();
        }

        final List<InterestEntity> interests = user.getInterests();

        if (interests == null) {
            return new ArrayList<>();
        }

        return interestMapper.apply(interests);
    }

    public InterestDto addToUser(final long userId, final String interestName) {
        final InterestEntity interest = getInterestForName(interestName);
        final UserProfileData user = userProfileDataRepository.findOne(userId);
        final List<UserProfileData> interestUsers = interest.getUsers() == null
                ? new ArrayList<>()
                : interest.getUsers();

        for (final UserProfileData interestUser : interestUsers) {
            if (user.equals(interestUser)) {
                return interestMapper.apply(interest);
            }
        }

        interestUsers.add(user);
        return interestMapper.apply(interest);
    }

    public void removeFromUser(final long userId, final String interestName) {
        final UserProfileData user = userProfileDataRepository.findOne(userId);

        if (user.getInterests() != null) {
            InterestEntity removable = null;

            for (final InterestEntity interest : user.getInterests()) {
                if (interest.getName().equals(interestName)) {
                    removable = interest;
                }
            }

            if (removable != null) {
                user.getInterests().remove(removable);
            }

            userProfileDataRepository.saveAndFlush(user);
        }
    }

    public List<InterestDto> getChannelInterests(final long channelId) {
        final ChannelProfileEntity user = channelRepository.findOne(channelId);

        if (user == null) {
            return new ArrayList<>();
        }

        final List<InterestEntity> interests = user.getInterests();

        if (interests == null) {
            return new ArrayList<>();
        }

        return interestMapper.apply(interests);
    }

    public InterestDto addToChannel(final long channelId, final String interestName) {
        final InterestEntity interest = getInterestForName(interestName);
        final ChannelProfileEntity channel = channelRepository.findOne(channelId);
        final List<ChannelProfileEntity> interestChannels = interest.getChannels() == null
                ? new ArrayList<>()
                : interest.getChannels();

        for (final ChannelProfileEntity interestChannel : interestChannels) {
            if (channel.equals(interestChannel)) {
                return interestMapper.apply(interest);
            }
        }

        interestChannels.add(channel);
        return interestMapper.apply(interest);
    }

    public void removeFromChannel(final long channelId, final String interestName) {
        final ChannelProfileEntity channel = channelRepository.findOne(channelId);

        if (channel.getInterests() != null) {
            InterestEntity removable = null;

            for (final InterestEntity interest : channel.getInterests()) {
                if (interest.getName().equals(interestName)) {
                    removable = interest;
                }
            }

            if (removable != null) {
                channel.getInterests().remove(removable);
            }

            channelRepository.saveAndFlush(channel);
        }
    }

    private InterestEntity getInterestForName(final String interestName) {
        final List<InterestEntity> interests = interestRepository.findByName(interestName);
        return interests.size() > 0
                ? interests.get(0)
                : createInterest(interestName);
    }

    private InterestEntity createInterest(final String interestName) {
        final InterestEntity interest = new InterestEntity();
        interest.setName(interestName);
        interest.setCreatedAt(new Date());
        return interestRepository.saveAndFlush(interest);
    }
}
