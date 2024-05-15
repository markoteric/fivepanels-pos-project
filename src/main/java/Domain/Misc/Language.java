package Domain.Misc;

import java.util.Set;

public class Language {
    private Set<String> languages;

    public Set<String> getLanguages() {

        return languages;
    }

    public void setLanguages(Set<String> languages) {

        Assertion.isNotNull(languages, "languages");
        Assertion.isNotBlank(languages.toString(), "languages");
        this.languages = languages;
    }
}
