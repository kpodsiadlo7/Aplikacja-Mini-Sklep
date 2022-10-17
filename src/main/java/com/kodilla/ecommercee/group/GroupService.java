package com.kodilla.ecommercee.group;

import com.kodilla.ecommercee.exceptions.NoFoundGroupException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    GroupService(final GroupRepository groupRepository, final GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    public List<GroupDto> getAllGroups(){
        return groupMapper.mapToGroupDtoList(groupRepository.findAll());
    }
    public Group getGroup(Long groupId) throws NoFoundGroupException {
        return groupRepository.findById(groupId).orElseThrow(NoFoundGroupException::new);
    }
    public GroupDto getGroupById(Long groupId) throws NoFoundGroupException {
        Group group = groupRepository.findById(groupId).orElseThrow(NoFoundGroupException::new);
        return groupMapper.mapToGroupDto(group);
    }
    public Group updateGroupById(Long groupId,GroupDto groupDto) throws NoFoundGroupException {
        if (groupRepository.existsById(groupId)) {
            Group groupUpdated = groupMapper.mapToGroup(groupDto);
            groupRepository.save(groupUpdated);
            return groupUpdated;
        }
        throw new NoFoundGroupException();
    }
    public void deleteGroupById(Long groupId) throws NoFoundGroupException {
        if (!groupRepository.existsById(groupId))
            throw new NoFoundGroupException();
        groupRepository.deleteById(groupId);
    }
    public void createGroup(GroupDto groupDto){
        Group group = groupMapper.mapToGroup(groupDto);
        groupRepository.save(group);
    }
}
