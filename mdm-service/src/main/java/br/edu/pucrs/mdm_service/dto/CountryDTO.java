package br.edu.pucrs.mdm_service.dto;

import java.util.List;
import java.util.Map;

public class CountryDTO {
    public Name name;
    public List<String> tld;
    public String cca2;
    public String ccn3;
    public String cioc;
    public boolean independent;
    public String region;
    public String subregion;
    public List<String> capital;
    public long population;
    public List<String> borders;
    public double area;
    public boolean landlocked;
    public Map<String, Translation> translations;
    public Flags flags;

    public static class Name {
        public String common;
        public String official;
        public Map<String, NativeName> nativeName;
    }

    public static class NativeName {
        public String official;
        public String common;
    }

    public static class Flags {
        public String png;
        public String svg;
        public String alt;
    }

    public static class Translation {
        public String official;
        public String common;
    }
}
