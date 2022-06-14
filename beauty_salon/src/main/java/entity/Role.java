package entity;


import java.util.Objects;

public class Role  {

    private Integer idRole;
    private String codename;
    private String description;

    public Role(Integer idRole, String codename, String description)
    {
        this.idRole = idRole;
        this.codename = codename;
        this.description = description;
    }

    public Role(String codename, String description) {
        this.codename = codename;
        this.description = description;
    }
    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return idRole == role.idRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + idRole +
                ", username='" + codename + '\'' +
                ", password='" + description + '\'' +
                '}';
    }
}
