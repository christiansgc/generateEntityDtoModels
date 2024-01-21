package cm.aites.dev.backend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Getter
@Setter
@NoArgsConstructor
public class ForeignFieldEntityDto {
    private TypeJpaEntityRelationship typeRelationship;
    private FetchType fetchType;
    private String entityFKName;
    //private String dbFKName;
    //private String entityRefColumnName;
    private String dbRefColumnName;
    private String jsonBackReference;
    private String jsonManagedReference;
    private CascadeType onUpdateAction;
    private CascadeType onDeleteAction;
}
