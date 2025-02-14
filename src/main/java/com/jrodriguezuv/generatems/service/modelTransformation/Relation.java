package com.jrodriguezuv.generatems.service.modelTransformation;

public class Relation {
    

    public String relationId;
    public String relationType;
    public String relationMultiplicityStart;
    public String relationRoleNameStart;
    public String relationClassStar;
    public String relationClassIdStart;
    public String relationMultiplicityEnd;
    public String relationRoleNameEnd;
    public String relationClassEnd;
    public String RelationClassIdEnd;
    

    public String getRelationClassIdStart() {
        return relationClassIdStart;
    }

    public void setRelationClassIdStart(String relationClassIdStart) {
        this.relationClassIdStart = relationClassIdStart;
    }

    public String getRelationClassIdEnd() {
        return RelationClassIdEnd;
    }

    public void setRelationClassIdEnd(String relationClassIdEnd) {
        RelationClassIdEnd = relationClassIdEnd;
    }

    

    public Relation(String relationId, String relationType, String relationMultiplicityStart,
            String relationRoleNameStart, String relationClassStar, String relationClassIdStart,
            String relationMultiplicityEnd, String relationRoleNameEnd, String relationClassEnd,
            String relationClassIdEnd) {
        this.relationId = relationId;
        this.relationType = relationType;
        this.relationMultiplicityStart = relationMultiplicityStart;
        this.relationRoleNameStart = relationRoleNameStart;
        this.relationClassStar = relationClassStar;
        this.relationClassIdStart = relationClassIdStart;
        this.relationMultiplicityEnd = relationMultiplicityEnd;
        this.relationRoleNameEnd = relationRoleNameEnd;
        this.relationClassEnd = relationClassEnd;
        RelationClassIdEnd = relationClassIdEnd;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getRelationMultiplicityStart() {
        return relationMultiplicityStart;
    }

    public void setRelationMultiplicityStart(String relationMultiplicityStart) {
        this.relationMultiplicityStart = relationMultiplicityStart;
    }

    public String getRelationRoleNameStart() {
        return relationRoleNameStart;
    }

    public void setRelationRoleNameStart(String relationRoleNameStart) {
        this.relationRoleNameStart = relationRoleNameStart;
    }

    public String getRelationClassStar() {
        return relationClassStar;
    }

    public void setRelationClassStar(String relationClassStar) {
        this.relationClassStar = relationClassStar;
    }

    public String getRelationMultiplicityEnd() {
        return relationMultiplicityEnd;
    }

    public void setRelationMultiplicityEnd(String relationMultiplicityEnd) {
        this.relationMultiplicityEnd = relationMultiplicityEnd;
    }

    public String getRelationRoleNameEnd() {
        return relationRoleNameEnd;
    }

    public void setRelationRoleNameEnd(String relationRoleNameEnd) {
        this.relationRoleNameEnd = relationRoleNameEnd;
    }

    public String getRelationClassEnd() {
        return relationClassEnd;
    }

    public void setRelationClassEnd(String relationClassEnd) {
        this.relationClassEnd = relationClassEnd;
    }

    @Override
    public String toString() {
        return "Relation [relationId=" + relationId + ", relationType=" + relationType + ", relationMultiplicityStart="
                + relationMultiplicityStart + ", relationRoleNameStart=" + relationRoleNameStart
                + ", relationClassStar=" + relationClassStar + ", relationMultiplicityEnd=" + relationMultiplicityEnd
                + ", relationRoleNameEnd=" + relationRoleNameEnd + ", relationClassEnd=" + relationClassEnd + "]";
    }

    


    
    


    
    

    
}
