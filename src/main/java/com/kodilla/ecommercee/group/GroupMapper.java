package com.kodilla.ecommercee.group;

import com.kodilla.ecommercee.product.Product;
import com.kodilla.ecommercee.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupMapper {
    ProductRepository productRepository;

    public Group mapToGroup(final GroupDto groupDto){
        return new Group(
                groupDto.getName()
        );
    }

    public GroupDto mapToGroupDto(final Group group){
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getProducts().stream().map(Product::getId).collect(Collectors.toList())
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupsList){
        return groupsList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }

}
