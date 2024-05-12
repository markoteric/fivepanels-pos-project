package User;

import Media.Media;
import Misc.Hashtag;
import Misc.Language;
import Enum.MedicalTitle;
import Enum.Specialization;

import java.util.Set;

public class UserProfile {


    private Media profilePicture;
    private Set<Specialization> specializations;
    private Set<MedicalTitle> medicalTitles;
    private Set<Hashtag> experience;
    private String country;
    private String city;
    private Set<Language> languages;
    private Integer activityScore;
    private Integer experienceScore;
}
