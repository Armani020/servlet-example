package database;

import models.Organization;

import java.util.*;

public class OrganizationDatabase {

    private Set<Organization> users = new LinkedHashSet<>(Arrays.asList(
            new Organization(100L, "First organization", "First title"),
            new Organization(101L, "Second organization", "Second title"),
            new Organization(102L, "Third organization", "Third title")
    ));

    public Set<Organization> getOrganizations() {
        return users;
    }
}
