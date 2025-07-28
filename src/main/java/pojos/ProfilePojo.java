package pojos;

public class ProfilePojo {

    public ProfilePojo() {
    }
    private String status;
    private String skills;
    private String company;
    private String website;
    private String location;
    private String bio;
    private String githubUserName;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGithubUserName() {
        return githubUserName;
    }

    public void setGithubUserName(String githubUserName) {
        this.githubUserName = githubUserName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public ProfilePojo(String status, String skills, String company, String website, String location, String bio, String githubUserName) {
        this.status = status;
        this.skills = skills;
        this.company = company;
        this.website = website;
        this.location = location;
        this.bio = bio;
        this.githubUserName = githubUserName;
    }

    @Override
    public String toString() {
        return "ProfilePojo{" +
                "status='" + status + '\'' +
                ", skills='" + skills + '\'' +
                ", company='" + company + '\'' +
                ", website='" + website + '\'' +
                ", location='" + location + '\'' +
                ", bio='" + bio + '\'' +
                ", githubUserName='" + githubUserName + '\'' +
                '}';
    }
}
