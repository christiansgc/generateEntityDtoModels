package ${entityDtoSpec.packageEntityClass};

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import cm.aites.dev.backend.bibliotools.model.AuditColumns;
import cm.aites.dev.backend.bibliotools.model.AuditColumnsInterface;
import cm.aites.dev.backend.bibliotools.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "${entityDtoSpec.dbTableName}")
public class ${entityDtoSpec.entityName} extends BaseEntity{
private static final long serialVersionUID = 1L;

<#list entityDtoSpec.listeFields as field>
<#if entityDtoSpec.countPrimaryKeys()==1 && field.isPrimaryKey()==true>
    @Id
    <#if field.generatedValue==true && field.generationType?has_content >
    @GeneratedValue(strategy = GenerationType.${field.generationType})
    </#if>
</#if>

<#if !field.referenceFK?has_content>
    <#if field.isTypeData_StringLob()==true>
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    </#if>
    <#if field.transientField?has_content && field.transientField==true>
    @Transient
    <#else>
    @Column(name = "${field.dbFieldName}" <#if field.nullable?has_content>, nullable = ${field.nullable?string('true','false')}</#if> <#if field.unique?has_content>, unique = ${field.unique?string('true','false')} </#if> <#if field.maxLength?has_content && field.isStringData()==true>, length = ${field.maxLength}</#if>)
    </#if>
    private ${field.typeFieldData.getValeur()} ${field.entityFieldName};
<#else>
    @${field.referenceFK.typeRelationship.getValeur()}(fetch = FetchType.${field.referenceFK.fetchType}, optional = ${field.nullable?string('true','false')})
    @JoinColumn(name = "${field.dbFieldName}" <#if field.nullable?has_content>, nullable = ${field.nullable?string('true','false')}</#if> <#if field.unique?has_content>, unique = ${field.unique?string('true','false')} </#if> <#if field.maxLength?has_content && field.isStringData()==true>, length = ${field.maxLength}</#if>, referencedColumnName = "${field.referenceFK.dbRefColumnName}")
    <#if field.referenceFK.jsonBackReference?has_content && field.referenceFK.jsonBackReference?trim != ''>
    @JsonBackReference(value = "${field.referenceFK.jsonBackReference}")
    </#if>
    <#if field.referenceFK.jsonManagedReference?has_content && field.referenceFK.jsonManagedReference?trim != ''>
    @jsonManagedReference(value = "${field.referenceFK.jsonManagedReference}")
    </#if>
    private ${field.referenceFK.entityFKName} ${field.entityFieldName};
</#if>
</#list>
}