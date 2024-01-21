package ${entityDtoSpec.packageDtoClass}.${entityDtoSpec.entityName};

import cm.aites.dev.backend.bibliotools.controller.dto.BaseDTOMappingEntityTracking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter @Setter
@JsonIgnoreProperties(value = {
BaseDTOMappingEntityTracking.field_tracker_datecreation,
BaseDTOMappingEntityTracking.field_tracker_datelastmodification,
BaseDTOMappingEntityTracking.field_tracker_modifybyuserid,
BaseDTOMappingEntityTracking.field_tracker_modifybyuserlogin
})
public class ${entityDtoSpec.entityName}DTOExport extends ${entityDtoSpec.entityName}DTO {
}