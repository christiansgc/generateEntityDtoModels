package ${entityDtoSpec.packageDtoClass}.${entityDtoSpec.entityName};

import ${entityDtoSpec.packageEntityClass}.${entityDtoSpec.entityName};
import cm.aites.dev.backend.bibliotools.controller.dto.BaseDTOMappingEntity;
import cm.aites.dev.backend.bibliotools.controller.dto.BaseDTOMappingEntityTracking;
import cm.aites.dev.backend.bibliotools.controller.dto.ColumnDTOMappingAnnotation;
import cm.aites.dev.backend.bibliotools.controller.dto.CrudViewAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class ${entityDtoSpec.entityName}DTO extends BaseDTOMappingEntity<${entityDtoSpec.entityName}>{
    @JsonIgnore
    @Autowired
    protected ApplicationPropertiesConfiguration appConfProperties;
    <!--assign pk = r"{entityDtoSpec.findFirstPrimaryKey()}"-->
    <#if entityDtoSpec.findFirstPrimaryKey()?has_content>public static final String field_dto_id = "${entityDtoSpec.findFirstPrimaryKey().dtoFieldName}";</#if>


<#list entityDtoSpec.listeFields as field>
    @ColumnDTOMappingAnnotation(entityColumnName = "${field.entityFieldName}")
    private ${field.typeFieldData.getValeur()} ${field.dtoFieldName};
</#list>
}