package br.com.gubee.interview.model;

import java.util.UUID;

public class HeroComparison {
    private UUID id1;
    private UUID id2;
    private int strengthDifference;
    private int agilityDifference;
    private int dexterityDifference;
    private int intelligenceDifference;

    public UUID getId1() {
        return id1;
    }

    public void setId1(UUID id1) {
        this.id1 = id1;
    }

    public UUID getId2() {
        return id2;
    }

    public void setId2(UUID id2) {
        this.id2 = id2;
    }

    public int getStrengthDifference() {
        return strengthDifference;
    }

    public void setStrengthDifference(int strengthDifference) {
        this.strengthDifference = strengthDifference;
    }

    public int getAgilityDifference() {
        return agilityDifference;
    }

    public void setAgilityDifference(int agilityDifference) {
        this.agilityDifference = agilityDifference;
    }

    public int getDexterityDifference() {
        return dexterityDifference;
    }

    public void setDexterityDifference(int dexterityDifference) {
        this.dexterityDifference = dexterityDifference;
    }

    public int getIntelligenceDifference() {
        return intelligenceDifference;
    }

    public void setIntelligenceDifference(int intelligenceDifference) {
        this.intelligenceDifference = intelligenceDifference;
    }

    public HeroComparison(UUID id1, UUID id2, int strengthDifference, int agilityDifference, int dexterityDifference, int intelligenceDifference) {
        this.id1 = id1;
        this.id2 = id2;
        this.strengthDifference = strengthDifference;
        this.agilityDifference = agilityDifference;
        this.dexterityDifference = dexterityDifference;
        this.intelligenceDifference = intelligenceDifference;
    }
}
