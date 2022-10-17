package com.kodilla.ecommercee.group;

import com.kodilla.ecommercee.exceptions.NoFoundGroupException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    GroupService groupService;
    GroupRepository groupRepository;

    GroupController(final GroupService groupService, final GroupRepository groupRepository) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }
    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) throws NoFoundGroupException {
        return ResponseEntity.ok(groupService.getGroupById(groupId));
    }
    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        groupService.createGroup(groupDto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("{groupId}")
    public ResponseEntity<Group> updateGroup(@RequestBody GroupDto groupDto, @PathVariable Long groupId) throws NoFoundGroupException {
        return ResponseEntity.ok(groupService.updateGroupById(groupId,groupDto));
    }
    @DeleteMapping(value = "{groupId}")
    ResponseEntity<Void> deleteGroupById(@PathVariable Long groupId) throws NoFoundGroupException {
        groupService.deleteGroupById(groupId);
        return ResponseEntity.ok().build();
    }
}
