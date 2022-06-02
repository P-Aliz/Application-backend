package edu.bbte.allamv.paim1943.mapper;

import edu.bbte.allamv.paim1943.dto.UserInDto;
import edu.bbte.allamv.paim1943.dto.UserOutDto;
import edu.bbte.allamv.paim1943.model.User;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User getFromDto(UserInDto userInDto);

    public abstract UserOutDto dtoFromModel(User user);

    public abstract UserOutDto dtoFromModel(Optional<User> user);

    /*@IterableMapping(elementTargetType = ChefOutDto.class)
    public abstract Collection<ChefOutDto> dtosFromModels(Collection<Chef> chefs);*/

}
