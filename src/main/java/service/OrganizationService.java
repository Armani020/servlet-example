package service;

import database.OrganizationDatabase;
import models.Organization;

import java.util.Set;

public class OrganizationService {

    OrganizationDatabase db = new OrganizationDatabase();

    public Set<Organization> getOrganizations() {
        return db.getOrganizations();
    }

    public Boolean addOrganization(Organization organization) {
        if (isValid(organization)) {
            return db.getOrganizations().add(organization);
        }
        return false;
    }

    public Boolean deleteOrganization(Long id) {
        Organization delete = db.getOrganizations().stream().filter(organization -> id.equals(organization.getId())).findAny().orElse(null);
        if (delete == null) {
            return false;
        }
        return db.getOrganizations().remove(delete);
    }

    public boolean isValid(Organization organization) {
        return organization.getTitle() != null && organization.getAddress() != null;
    }
}
