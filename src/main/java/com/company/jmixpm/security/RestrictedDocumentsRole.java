package com.company.jmixpm.security;

import com.company.jmixpm.entity.Document;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "RestrictedDocuments", code = RestrictedDocumentsRole.CODE)
public interface RestrictedDocumentsRole {
    String CODE = "restricted-documents";

    @JpqlRowLevelPolicy(entityClass = Document.class,
            join = "join {E}.project proj",
            where = "proj.manager.id = :current_user_id" +
                    " or {E}.createdBy = :current_user_username")
    void document();
}